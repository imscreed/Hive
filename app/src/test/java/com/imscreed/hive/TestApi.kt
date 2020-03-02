package com.imscreed.hive

import com.imscreed.hive.api.EmployeeApi
import com.imscreed.hive.model.EmployeeResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TestEmployeeApi {
    companion object {
        fun create(): TestEmployeeApi {
            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return retrofit.create(TestEmployeeApi::class.java)
        }
    }

    @GET("employees_malformed.json")
    fun getEmployees(): Deferred<Response<EmployeeResponse>>

}