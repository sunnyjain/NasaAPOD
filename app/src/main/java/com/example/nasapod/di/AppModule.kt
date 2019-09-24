package com.example.nasapod.di

import android.app.Application
import android.content.Context
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

  /* @Singleton
    @Provides
    fun provideDb(app: Application): Scans {
        return Room
                .databaseBuilder(app, Scans::class.java, "Scans.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(db: Scans): ScansDao {
        return db.scansDao()
    }*/

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

}
