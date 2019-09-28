package com.example.nasapod.detail.model

import android.content.SharedPreferences
import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.extensions.*
import com.example.nasapod.networking.Outcome
import com.example.nasapod.networking.Scheduler
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

    override fun fetchAPODDetailList(id: Long) {
        fetchAPODDetailListOutcome.loading(true)
        val startIndex = if( (id - 5) >= 0) id - 5 else 0
        localData.getAPODList(startIndex, startIndex+10)
            .performOnBackOutOnMain(scheduler)
            .subscribe({
                fetchAPODDetailListOutcome.success(it)
            },{
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