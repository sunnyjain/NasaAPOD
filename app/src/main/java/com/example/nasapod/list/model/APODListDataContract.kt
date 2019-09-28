package com.example.nasapod.list.model

import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.networking.Outcome
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface APODListDataContract {
    interface Repository {
        val fetchApodListOutCome: PublishSubject<Outcome<List<APODObject>>>
        fun fetchAPODList()
        fun refereshAPODList(startDate: String, endDate: String)
        fun saveAPODRecords(apods: List<APODObject>)
        fun loadMoreData()
        fun updateStartAndEndIndexes()
        fun handleError(error: Throwable)
    }

    interface Local {
        fun getAPODList(startIndex: Long, endIndex: Long): Maybe<List<APODObject>>
        fun getMinDateAvailable(): Single<String>
        fun getLastRecordId(): Single<Long>
        fun saveAPODList(apods: List<APODObject>): Single<List<Long>>
    }

    interface Remote {
        fun getAPODList(apiKey: String, startDate: String, endDate: String): Single<List<APODObject>>
    }
}