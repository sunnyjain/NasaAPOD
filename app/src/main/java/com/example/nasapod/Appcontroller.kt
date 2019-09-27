package com.example.nasapod

import android.app.Activity
import android.app.Application
import com.example.nasapod.di.AppInjector
import com.example.nasapod.networking.FetchIt
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class Appcontroller: Application(), HasActivityInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initFetchIt()
        AppInjector.init(this)
        initStetho()

    }

    private fun initFetchIt() {
        FetchIt.init(this)
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}