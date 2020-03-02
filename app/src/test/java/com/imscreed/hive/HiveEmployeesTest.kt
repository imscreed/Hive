package com.imscreed.hive

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.google.common.truth.Truth.assertThat
import com.imscreed.hive.features.employeelist.EmployeeListViewModel
import com.imscreed.hive.model.EmployeeResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class HiveEmployeesTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val server = MockWebServer()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = CoroutineTestRule()

    interface TestEmployeeApi {
        @GET("employees_malformed.json")
        fun getEmployees(): Deferred<Response<String>>
    }

    private lateinit var viewModel: EmployeeListViewModel
    private lateinit var employeeApi: TestEmployeeApi

    @Before
    fun setUp() {
        viewModel = EmployeeListViewModel()

        val retrofit = Retrofit.Builder().baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        employeeApi = retrofit.create(TestEmployeeApi::class.java)
    }

    @Test
    fun `verify EmployeeListLiveData returnsGivenValue`() {

        val employeeList = MockTestUtil.mockEmployeeList()
        val subject = viewModel._employeesLiveData

        coroutineRule.runBlockingTest {
            subject.postValue(employeeList)
        }

        subject.observeOnce {
            println("Expected -> $employeeList")
            println("Actual -> $it")
            assertEquals(employeeList, it)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun bodySuccess200() = runBlocking {
        val employeeListString = "Test"
        server.enqueue(MockResponse().setBody(employeeListString))
        val deferred = employeeApi.getEmployees()
        assertThat(deferred.await()).isEqualTo("Test")
    }

    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }

}


