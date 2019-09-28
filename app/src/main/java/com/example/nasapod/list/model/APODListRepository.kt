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
    private val localData: APODListLocalData,
    private val remoteData: APODListRemoteData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable,
    private val sharedPres: SharedPreferences
) : APODListDataContract.Repository {


    private var startIndex = 1L
    private var endIndex = 22L

    override fun updateStartAndEndIndexes() {
        fetchApodListOutCome.loading(true)
        localData.getLastRecordId()
            .performOnBackOutOnMain(scheduler)
            .subscribe({
                //update the values accordingly from here
                if (it > endIndex) {
                    startIndex = endIndex + 1
                    this.endIndex += 20L
                    fetchAPODList()
                } else {
                    loadMoreData()
                }
                Log.e("indexes", startIndex.toString().plus(endIndex))
            }, {
                fetchApodListOutCome.failed(it)
            })
            .addTo(compositeDisposable)
    }

    override val fetchApodListOutCome: PublishSubject<Outcome<List<APODObject>>> =
        PublishSubject.create()

    override fun fetchAPODList() {
        fetchApodListOutCome.loading(true)
        localData.getAPODList(startIndex, endIndex)
            .performOnBackOutOnMain(scheduler)
            .doAfterSuccess {
                if (it.isEmpty()) {
                    if (FetchIt.shouldFetchAPODList()) {
                        //load records for first time.
                        val calendar = Calendar.getInstance()
                        calendar.add(Calendar.DAY_OF_YEAR, -1)

                        val lastFetchedDate = sharedPres.getString(
                            LAST_FETCH_DATE,
                            SimpleDateFormat(
                                "yyyy-MM-dd",
                                Locale.getDefault()
                            ).format(calendar.time)
                        ) ?: ""

                        calendar.add(Calendar.DAY_OF_YEAR, -20)

                        refereshAPODList(
                            SimpleDateFormat(
                                "yyyy-MM-dd",
                                Locale.getDefault()
                            ).format(calendar.time), lastFetchedDate
                        )
                    }
                    fetchApodListOutCome.loading(false)
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
                if(it.last() > 20) {
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