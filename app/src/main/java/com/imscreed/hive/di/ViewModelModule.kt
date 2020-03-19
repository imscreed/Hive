package com.imscreed.hive.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imscreed.hive.di.annotations.ViewModelKey
import com.imscreed.hive.features.employeelist.EmployeeListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EmployeeListViewModel::class)
    abstract fun bindEmployeeListViewModel(employeeListViewModel: EmployeeListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}