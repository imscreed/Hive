package com.imscreed.hive.repository

import com.imscreed.hive.api.EmployeeApi
import com.imscreed.hive.base.BaseRepository
import com.imscreed.hive.model.Employee
import com.imscreed.hive.persistence.EmployeeDao
import com.imscreed.hive.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository
@Inject constructor(private val employeeApi: EmployeeApi, val employeeDao: EmployeeDao) : BaseRepository() {

    suspend fun fetchEmployees(): MutableList<Employee>? {
        return if(NetworkUtil.isConnectedToNetwork){
            fetchEmployeesFromRemote()
        } else {
            fetchEmployeesFromDB()
        }
    }

    private fun fetchEmployeesFromDB(): MutableList<Employee> {
        return employeeDao.getAllEmployees()
    }

    suspend fun fetchEmployeesFromRemote(): MutableList<Employee>? {
        val response = secureApiCall(
            call = {
                withContext(Dispatchers.IO) {
                    employeeApi.getEmployeesFromRemoteAsync().await()
                }
            },
            errorMessage = "Error Fetching Employees"
        )

        if(response != null){
            saveToDB(response.employees.toMutableList())
        }
        return response?.employees?.toMutableList()
    }

    fun saveToDB(employees: List<Employee>) {
        employeeDao.saveEmployees(employees)
    }

}
