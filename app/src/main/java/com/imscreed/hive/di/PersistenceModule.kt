package com.imscreed.hive.di

import android.app.Application
import androidx.room.Room
import com.imscreed.hive.persistence.EmployeeDao
import com.imscreed.hive.persistence.HiveDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Provides
    @Singleton
    fun provideHiveDatabase(application: Application) : HiveDatabase {
        return Room
            .databaseBuilder(application, HiveDatabase::class.java, "Hive.db")
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideEmployeeDao(database: HiveDatabase) : EmployeeDao {
        return database.employeeDao()
    }
}