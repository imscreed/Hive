package com.imscreed.hive.repository

import com.imscreed.hive.api.EmployeeApi
import com.imscreed.hive.base.BaseRepository
import com.imscreed.hive.model.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository
@Inject constructor(private val employeeApi: EmployeeApi) : BaseRepository() {

    suspend fun fetchEmployeesFromRemote(): MutableList<Employee>? {
        val response = secureApiCall(
            call = {
                withContext(Dispatchers.IO) {
                    employeeApi.getEmployeesFromRemoteAsync().await()
                }
            },
            errorMessage = "Error Fetching Employees"
        )

        return response?.employees?.toMutableList()
    }
}
