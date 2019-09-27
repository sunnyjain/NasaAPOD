package com.example.nasapod.list.model

import com.example.nasapod.commons.data.local.APODObject
import com.example.nasapod.commons.data.remote.ApodService
import io.reactivex.Single
import javax.inject.Inject

class APODListRemoteData @Inject constructor(private val apodService: ApodService) : APODListDataContract.Remote {
    override fun getAPODList(
        apiKey: String,
        startDate: String,
        endDate: String
    ): Single<List<APODObject>> = apodService.getAPODList(apiKey, startDate, endDate)
}