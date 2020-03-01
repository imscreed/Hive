package com.imscreed.hive.di

import com.imscreed.hive.HiveApplication
import com.imscreed.hive.features.employeelist.EmployeeListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface ApplicationComponent {
    fun inject(application: HiveApplication)
    fun inject(employeeListViewModel: EmployeeListViewModel)

    @Component.Builder
    interface Builder {
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): ApplicationComponent
    }
}