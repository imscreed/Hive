package com.imscreed.hive.persistence

import androidx.room.TypeConverter
import com.imscreed.hive.model.EmployeeType

class Converters {
    @TypeConverter
    fun fromStringToEmployeeType(value: String) : EmployeeType {
        return when(value){
            EmployeeType.FULL_TIME.toString() -> EmployeeType.FULL_TIME
            EmployeeType.PART_TIME.toString() -> EmployeeType.PART_TIME
            EmployeeType.CONTRACTOR.toString() -> EmployeeType.CONTRACTOR
            else -> EmployeeType.FULL_TIME
        }
    }

    @TypeConverter
    fun toStringFromEmployeeType(value: EmployeeType) : String {
        return value.toString()
    }
}