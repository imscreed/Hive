package com.imscreed.hive.di

import android.app.Application
import com.imscreed.hive.HiveApplication
import com.imscreed.hive.features.employeelist.EmployeeListViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        FragmentModule::class,
        NetworkModule::class,
        PersistenceModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<HiveApplication>{
    fun inject(employeeListViewModel: EmployeeListViewModel)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
//        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ApplicationComponent
    }
}