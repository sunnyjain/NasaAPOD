package com.example.nasapod.commons.data.remote

import com.example.nasapod.commons.data.local.APODObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodService {
    @GET("/planetary/apod")
    fun getAPODList(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Single<List<APODObject>>
}