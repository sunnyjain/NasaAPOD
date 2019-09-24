package com.example.nasapod.di

import androidx.lifecycle.ViewModelProvider
import com.example.nasapod.viewmodel.NasaPODViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {


/*    @Binds
    @IntoMap
    @ViewModelKey(ScannerPageViewModel::class)
    abstract fun bindTaskListViewModel(taskListViewModel: ScannerPageViewModel): ViewModel*/
/*
    @Binds
    @IntoMap
    @ViewModelKey(AddTaskViewModel::class)
    abstract fun bindAddTaskListViewModel(addTaskViewModel: AddTaskViewModel): ViewModel
*/

    @Binds
    abstract fun bindViewModelFactory(factory: NasaPODViewModelFactory): ViewModelProvider.Factory

}