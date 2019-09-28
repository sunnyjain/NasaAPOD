package com.example.nasapod.detail.model

import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.networking.Outcome
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface APODDEtailListDataContract {
    interface Repository {
        val fetchAPODDetailListOutcome: PublishSubject<Outcome<List<APODObject>>>
        fun fetchAPODDetailList(id: Long)
        fun refreshAPOdDetailList(startDate: String, endDAte: String)
        fun loadMoreData()
        fun handleError(error: Throwable)
    }

    interface Local {
        fun getAPODList(startIndex: Long, endIndex: Long): Maybe<List<APODObject>>
        fun getAPODObject(date: String): Single<APODObject>
    }

    interface Remote {
        fun getAPODList(apiKey: String, startDate: String, endDate: String): Single<APODObject>
    }
}