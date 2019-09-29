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

    override fun fetchAPODDetailList(id: Long, direction: Int) {
        fetchAPODDetailListOutcome.loading(true)
        Log.e("indexes", id.toString())

        val startIndex =
            when (direction) {
                BOTH -> {
                    if ((id - 5L) >= 1L) (id - 5L) else 1L
                }
                RIGHT -> {
                    id + 1L
                }
                LEFT -> {
                    if ((id - 11L) >= 1L) (id - 11L) else 1L
                }
                else -> 1L
            }

        val endIndex =
            when (direction) {
                BOTH, RIGHT -> {
                    startIndex + 10
                }
                LEFT -> {
                    if (id - 1L >= 1L) id - 1L else 1L
                }
                else -> 1L
            }

        Log.e("indexes",startIndex.toString().plus(" ").plus(endIndex))

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