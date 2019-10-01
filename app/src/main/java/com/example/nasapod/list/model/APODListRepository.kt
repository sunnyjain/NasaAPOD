package com.example.nasapod.list.model

import android.content.SharedPreferences
import android.util.Log
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.extensions.*
import com.example.nasapod.networking.FetchIt
import com.example.nasapod.networking.FetchItKeys.LAST_FETCH_DATE
import com.example.nasapod.networking.Outcome
import com.example.nasapod.networking.Scheduler
import com.example.nasapod.utils.Constants.API_KEY
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class APODListRepository @Inject constructor(
    private val localData: APODListDataContract.Local,
    private val remoteData: APODListDataContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : APODListDataContract.Repository {


    private val cal = Calendar.getInstance()
    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private lateinit var startDate: String
    private lateinit var endDate: String


    /*set this func first*/
    override fun setStartAndEndDate() {
        cal.add(Calendar.DAY_OF_YEAR, -1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        endDate = sdf.format(cal.time)

        cal.add(Calendar.DAY_OF_YEAR, -20)
        startDate = sdf.format(cal.time)
    }

    override fun updateStartAndEndIndexes() {
        fetchApodListOutCome.loading(true)
        localData.getLastRecordId()
            .performOnBackOutOnMain(scheduler)
            .subscribe({

                //basically the minimist date available.
                val lastAvailableDate = sdf.parse(it.date) ?: Date()

                if (lastAvailableDate < sdf.parse(startDate)) {
                    cal.time = sdf.parse(startDate) ?: Date()
                    cal.add(Calendar.DAY_OF_YEAR, -1)

                    endDate = sdf.format(cal.time)

                    cal.add(Calendar.DAY_OF_YEAR, -20)
                    startDate = sdf.format(cal.time)
                    fetchAPODList()
                } else {
                    loadMoreData()
                }
            }, {
                fetchApodListOutCome.failed(it)
            })
            .addTo(compositeDisposable)
    }

    override val fetchApodListOutCome: PublishSubject<Outcome<List<APODObject>>> =
        PublishSubject.create()

    override fun fetchAPODList() {
        fetchApodListOutCome.loading(true)
        localData.getAPODList(startDate, endDate)
            .performOnBackOutOnMain(scheduler)
            .doAfterSuccess {
                if (it.isEmpty()) {
                    if (FetchIt.shouldFetchAPODList()) {
                        //load records for first time.
                        refereshAPODList(startDate, endDate)
                    }
                } else {
                    fetchApodListOutCome.success(it)
                }
            }
            .subscribe({
            }, { error -> handleError(error) })
            .addTo(compositeDisposable)
    }


    /** this should basically give me updated or say new APOD.*/
    override fun refereshAPODList(startDate: String, endDate: String) {
        fetchApodListOutCome.loading(true)

        remoteData.getAPODList(
            API_KEY, startDate, endDate
        )
            .performOnBackOutOnMain(scheduler)
            .subscribe({
                saveAPODRecords(it)
            }, {
                handleError(it)
            }).addTo(compositeDisposable)
    }

    override fun saveAPODRecords(apods: List<APODObject>) {
        localData.saveAPODList(apods)
            .performOnBackOutOnMain(scheduler)
            .subscribe({
                //check condition to understand if the items loaded were first time.
                if(it.last() > 21) {
                    updateStartAndEndIndexes()
                } else {
                    fetchAPODList()
                }
            },{
                fetchApodListOutCome.failed(it)
            }).addTo(compositeDisposable)


    }

    override fun loadMoreData() {
        localData.getMinDateAvailable()
            .performOnBackOutOnMain(scheduler)
            .subscribe({
                val calendar = Calendar.getInstance()
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                calendar.time = sdf.parse(it) ?: calendar.time
                calendar.add(Calendar.DAY_OF_YEAR, 1)

                val endDate = sdf.format(calendar.time)
                calendar.add(Calendar.DAY_OF_YEAR, -20)
                val startDate = sdf.format(calendar.time)

                Log.e(
                    "loadmore", startDate.plus(endDate)
                )
                refereshAPODList(startDate, endDate)
            }, { handleError(it) })
            .addTo(compositeDisposable)
    }

    override fun handleError(error: Throwable) {
        fetchApodListOutCome.failed(error)
    }
}