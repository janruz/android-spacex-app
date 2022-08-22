package com.github.janruz.spacexapp.utilities

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.reflect.KClass

fun<T> runCatchingExceptions(vararg exceptions: KClass<out Throwable>, action: () -> T): Result<T> {
    return try {
        Result.success(action())
    } catch(e: Throwable) {
        if(!exceptions.contains(e::class)) {
            throw e
        } else {
            Result.failure(e)
        }
    }
}

suspend fun<T> runCatchingExceptionsSuspend(vararg exceptions: KClass<out Throwable>, action: suspend () -> T): Result<T> {
    return try {
        Result.success(action())
    } catch(e: Throwable) {
        if(!exceptions.contains(e::class)) {
            throw e
        } else {
            Result.failure(e)
        }
    }
}

suspend fun<T> runCatchingNetworkExceptions(action: suspend () -> T): Result<T> = runCatchingExceptionsSuspend<T>(
    HttpException::class,
    SocketTimeoutException::class,
    UnknownHostException::class,
) {
    action()
}