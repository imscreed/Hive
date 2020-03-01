package com.imscreed.hive.base

import androidx.lifecycle.ViewModel
import com.imscreed.hive.HiveApplication
import com.imscreed.hive.di.ApplicationComponent
import com.imscreed.hive.di.DaggerApplicationComponent
import com.imscreed.hive.di.NetworkModule
import com.imscreed.hive.features.employeelist.EmployeeListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ApplicationComponent = DaggerApplicationComponent
        .builder()
        .networkModule(NetworkModule(HiveApplication()))
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is EmployeeListViewModel -> injector.inject(this)
        }
    }
}