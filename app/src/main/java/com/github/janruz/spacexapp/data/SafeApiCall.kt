package com.github.janruz.spacexapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun<T> safeApiCall(apiCall: suspend () -> T): Result<T> = withContext(Dispatchers.IO) {
    try {
        Result.success(apiCall())
    } catch(e: Exception) {

        when(e) {
            is HttpException,
            is SocketTimeoutException,
            is UnknownHostException -> Result.failure(e)
            else -> throw e
        }
    }
}