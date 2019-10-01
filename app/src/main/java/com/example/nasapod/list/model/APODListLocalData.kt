package com.example.nasapod.list.model

import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.commons.data.local.ApodDB
import com.example.nasapod.extensions.addTo
import com.example.nasapod.extensions.performOnBack
import com.example.nasapod.extensions.performOnBackOutOnMain
import com.example.nasapod.networking.Scheduler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class APODListLocalData @Inject constructor(private val apodDB: ApodDB,
                                            private val scheduler: Scheduler
,private val compositeDisposable: CompositeDisposable) : APODListDataContract.Local {

    override fun getLastRecordId(): Single<APODObject> {
        return apodDB.apodDao().getLastRecordId()
    }


    override fun getMinDateAvailable(): Single<String> {
        return apodDB.apodDao().getMinDateAvailable()
    }

    override fun getAPODList(startDate: String, endDate: String): Maybe<List<APODObject>> {
        return apodDB.apodDao().getAPODList(startDate, endDate)
    }

    override fun saveAPODList(apods: List<APODObject>): Single<List<Long>> {
        return apodDB.apodDao().saveAPODs(apods)
    }
}