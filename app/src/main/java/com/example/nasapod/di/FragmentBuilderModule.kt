package com.example.nasapod.di


import com.example.nasapod.detail.ui.APODDetailView
import com.example.nasapod.list.ui.PODListView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributePODSListViewFragment(): PODListView

    @ContributesAndroidInjector
    abstract fun contributeAPODDetailViewFragment(): APODDetailView

}