package com.example.nasapod.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.nasapod.commons.data.local.ApodDB
import com.example.nasapod.commons.data.local.ApodDao
import com.squareup.picasso.Picasso

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
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

    /*providing picasso image object.*/
    @Provides
    @Singleton
    fun providesPicasso(): Picasso {
        return Picasso.get()
    }

    @Provides
    @Singleton
    fun providesApodService(retrofit: Retrofit) = retrofit.create(ApodService::class.java)
}
