package com.example.nasapod.test

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okio.Okio
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

/** should be used only for testing.*/
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object DependencyProvider {

    /** retrofit instance */
    fun getRetrofit(baseUrl : HttpUrl): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS).build())
            .build()
    }

    @SuppressLint("NewApi")
    fun getResponseFromJson(fileName: String): String {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("response/$fileName.json")
        val source = Okio.buffer(Okio.source(inputStream!!))
        return source.readString(StandardCharsets.UTF_8)
    }
}