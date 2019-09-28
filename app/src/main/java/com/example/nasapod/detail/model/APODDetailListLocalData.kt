package com.example.nasapod.detail.model

import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.commons.data.local.ApodDB
import com.example.nasapod.networking.Scheduler
import io.reactivex.Flowable
import io.reactivex.Single

import javax.inject.Inject

class APODDetailListLocalData @Inject constructor(
    private val apodDB: ApodDB
) : APODDEtailListDataContract.Local {

    override fun getAPODObject(date: String): Single<APODObject> {
        return apodDB.apodDao().getAPODObject(date)
    }

    override fun getAPODList(startIndex: Long, endIndex: Long): Flowable<List<APODObject>> {
        return apodDB.apodDao().getAPODList(startIndex, endIndex)
    }


}