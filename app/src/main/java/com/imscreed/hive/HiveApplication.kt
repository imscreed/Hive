package com.imscreed.hive

import android.app.Application
import com.imscreed.hive.di.ApplicationComponent
import com.imscreed.hive.di.NetworkModule
import com.imscreed.hive.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class HiveApplication : DaggerApplication() {
    private val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return initializeComponent()
    }

    private fun injectMembers() = appComponent.inject(this)
}