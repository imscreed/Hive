package com.imscreed.hive.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imscreed.hive.model.Employee

@Database(entities = [Employee::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class HiveDatabase: RoomDatabase() {
    abstract fun employeeDao() : EmployeeDao
}