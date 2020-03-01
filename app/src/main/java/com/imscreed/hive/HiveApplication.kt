package com.imscreed.hive

import android.app.Application
import com.imscreed.hive.di.ApplicationComponent
import com.imscreed.hive.di.NetworkModule
import com.imscreed.hive.di.DaggerApplicationComponent

class HiveApplication : Application() {
    private val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .builder()
            .networkModule(NetworkModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)
}