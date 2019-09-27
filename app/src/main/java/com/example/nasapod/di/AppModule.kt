package com.example.nasapod.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.nasapod.commons.data.local.ApodDB
import com.example.nasapod.commons.data.local.ApodDao
import com.example.nasapod.commons.data.remote.ApodService
import com.example.nasapod.networking.AppScheduler
import com.example.nasapod.networking.Scheduler
import com.example.nasapod.utils.Constants.PREFS
import com.squareup.picasso.Picasso

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton


//my core modules.
@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

   @Singleton
    @Provides
    fun provideDb(app: Application): ApodDB {
        return Room
                .databaseBuilder(app, ApodDB::class.java, "Apod.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideApodDao(db: ApodDB): ApodDao {
        return db.apodDao()
    }

    @Provides
    fun providesCompositeDisposableBag(): CompositeDisposable{
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun scheduler(): Scheduler {
        return AppScheduler()
    }

    /*providing picasso image object.*/
    @Provides
    @Singleton
    fun providesPicasso(): Picasso {
        return Picasso.get()
    }

    @Provides
    @Singleton
    fun providesApodService(retrofit: Retrofit) = retrofit.create(ApodService::class.java)

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

}
