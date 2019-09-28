package com.example.nasapod.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasapod.detail.viewmodel.APODDetailListViewModel
import com.example.nasapod.list.viewmodel.APODListViewModel
import com.example.nasapod.viewmodel.NasaPODViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(APODListViewModel::class)
    abstract fun bindApodListViewModel(apodListViewModel: APODListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(APODDetailListViewModel::class)
    abstract fun bindApodDetailListViewModel(apodDetailListViewModel: APODDetailListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: NasaPODViewModelFactory): ViewModelProvider.Factory

}