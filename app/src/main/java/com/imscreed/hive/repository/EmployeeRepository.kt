package com.imscreed.hive.repository

import com.imscreed.hive.api.EmployeeApi
import com.imscreed.hive.base.BaseRepository
import com.imscreed.hive.model.Employee
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository
@Inject constructor(private val employeeApi: EmployeeApi) : BaseRepository() {

    suspend fun fetchEmployeesFromRemote(): MutableList<Employee>? {
        val response = secureApiCall(
            call = { employeeApi.getEmployeesFromRemoteAsync().await() },
            errorMessage = "Error Fetching Employees"
        )

        return response?.employees?.toMutableList()
    }
}
