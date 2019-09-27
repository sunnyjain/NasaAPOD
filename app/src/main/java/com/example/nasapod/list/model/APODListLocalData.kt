package com.example.nasapod.list.model

import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.commons.data.local.ApodDB
import com.example.nasapod.extensions.performOnBack
import com.example.nasapod.networking.Scheduler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class APODListLocalData @Inject constructor(private val apodDB: ApodDB, private val scheduler: Scheduler) : APODListDataContract.Local {


    override fun getMinDateAvailable(): Single<String> {
        return apodDB.apodDao().getMinDateAvailable()
    }

    override fun getAPODList(): Flowable<List<APODObject>> {
        return apodDB.apodDao().getAPODList()
    }

    override fun saveAPODList(apods: List<APODObject>) {
        Completable.fromAction {
            apodDB.apodDao().saveAPODs(apods)
        }
            .performOnBack(scheduler)
            .subscribe()
    }
}