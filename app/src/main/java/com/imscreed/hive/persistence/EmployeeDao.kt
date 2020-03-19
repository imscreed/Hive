package com.imscreed.hive.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.imscreed.hive.model.Employee

@Dao
interface EmployeeDao {
    // Get all employees
    @Query("SELECT * FROM employees")
    fun getAllEmployees(): MutableList<Employee>
    // Insert all employees
    @Insert
    fun saveEmployees(employees: List<Employee>)
}