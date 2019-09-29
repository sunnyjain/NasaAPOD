package com.example.nasapod.detail.model

import android.content.SharedPreferences
import android.util.Log
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.extensions.*
import com.example.nasapod.networking.Outcome
import com.example.nasapod.networking.Scheduler
import com.example.nasapod.utils.Constants.BOTH
import com.example.nasapod.utils.Constants.LEFT
import com.example.nasapod.utils.Constants.RIGHT
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class APODDetailListRepository @Inject constructor(
    private val localData: APODDetailListLocalData,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable,
    private val sharedPres: SharedPreferences
) : APODDEtailListDataContract.Repository {

    override val fetchAPODDetailListOutcome = PublishSubject.create<Outcome<List<APODObject>>>()

    override fun fetchAPODDetailList(date: String, direction: Int) {
        fetchAPODDetailListOutcome.loading(true)

        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        calendar.time = sdf.parse(date) ?: Date()

        Log.e("date rceived", date)

        val startIndex =
            when(direction) {
                BOTH -> {
                    //get five records left and five on right.
                    calendar.add(Calendar.DAY_OF_YEAR, -5)
                    sdf.format(calendar.time)
                }
                LEFT -> {
                    //10 records on the left.
                    calendar.add(Calendar.DAY_OF_YEAR, 1)
                    sdf.format(calendar.time)
                }
                RIGHT -> {
                    //10 records on the right.
                    calendar.add(Calendar.DAY_OF_YEAR, -11)
                    sdf.format(calendar.time)
                }
                else -> sdf.format(Date())
            }

        calendar.time = sdf.parse(date) ?: Date() //resetting

        val endIndex =
            when(direction) {
                BOTH -> {
                    //get five records left and five on right.
                    calendar.add(Calendar.DAY_OF_YEAR, 5)
                    sdf.format(calendar.time)
                }
                LEFT -> {
                    //10 records on the left.
                    calendar.add(Calendar.DAY_OF_YEAR, 11)
                    sdf.format(calendar.time)
                }
                RIGHT -> {
                    //10 records on the right.
                    calendar.add(Calendar.DAY_OF_YEAR, -1)
                    sdf.format(calendar.time)
                }
                else -> sdf.format(Date())
            }

        Log.e("Dates", startIndex.plus(endIndex))

        if (startIndex != endIndex)
            localData.getAPODList(startIndex, endIndex)
                .performOnBackOutOnMain(scheduler)
                .subscribe({
                    fetchAPODDetailListOutcome.successWithDir(it, direction)
                }, {
                    fetchAPODDetailListOutcome.failed(it)
                })
                .addTo(compositeDisposable)
    }

    override fun refreshAPOdDetailList(startDate: String, endDAte: String) {

    }

    override fun loadMoreData() {

    }

    override fun handleError(error: Throwable) {

    }
}