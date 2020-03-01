package com.imscreed.hive.base

import android.util.Log
import com.imscreed.hive.model.Employee
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> secureApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: Result<T> = secureApiResult(call, errorMessage)
        var data: T? = null
        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("BaseRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }
        return data
    }

    private suspend fun <T : Any> secureApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body()!!)
        return Result.Error(IOException("Error when getting network response, Error Message - $errorMessage"))
    }
}