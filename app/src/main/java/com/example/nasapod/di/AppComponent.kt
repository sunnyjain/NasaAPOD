package com.example.nasapod.di

import android.app.Application
import com.example.nasapod.Appcontroller
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//my core component.
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                      AppModule::class,
                      NetworkModule::class,
                      RepositoryModule::class,
                      ActivityBuilder::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: Appcontroller)
}
