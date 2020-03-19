package com.imscreed.hive.di

import com.imscreed.hive.features.employeelist.EmployeeListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    /*
     * We define the name of the Fragment we are going
    * to inject the ViewModelFactory into. i.e. in our case
    * The name of the fragment: EmployeeListFragment
    */
    @ContributesAndroidInjector
    abstract fun contributeEmployeeListFragment(): EmployeeListFragment

}