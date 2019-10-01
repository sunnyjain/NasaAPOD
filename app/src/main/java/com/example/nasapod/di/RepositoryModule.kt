package com.example.nasapod.di

import com.example.nasapod.detail.model.APODDEtailListDataContract
import com.example.nasapod.detail.model.APODDetailListLocalData
import com.example.nasapod.detail.model.APODDetailListRepository
import com.example.nasapod.list.model.APODListDataContract
import com.example.nasapod.list.model.APODListLocalData
import com.example.nasapod.list.model.APODListRemoteData
import com.example.nasapod.list.model.APODListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsAPODListRepository(apodListRepository: APODListRepository): APODListDataContract.Repository

    @Binds
    abstract fun bindsAPODListLocalData(apodListLocalData: APODListLocalData): APODListDataContract.Local

    @Binds
    abstract fun bindsAPOdListRemoteData(apodListRemoteData: APODListRemoteData): APODListDataContract.Remote

    @Binds
    abstract fun bindsAPOdDetailListRepository(apodDetailListRepository: APODDetailListRepository): APODDEtailListDataContract.Repository

    @Binds
    abstract fun bindsAPODDetailListLocalData(apodDetailListLocalData: APODDetailListLocalData): APODDEtailListDataContract.Local


}