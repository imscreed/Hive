package com.imscreed.hive.api

import com.imscreed.hive.model.EmployeeResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {
    @GET("employees.json")
    fun getEmployeesFromRemoteAsync(): Deferred<Response<EmployeeResponse>>
}