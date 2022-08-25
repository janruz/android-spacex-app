package com.github.janruz.spacexapp.utilities

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.reflect.KClass

/**
 * Runs a supplied function catching all the specified exceptions thrown from it and encapsulating
 * them in a Kotlin Result. If there is a different exception thrown, this function lets it
 * propagate up.
 */
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

/**
 * Runs a supplied suspend function catching all the specified exceptions thrown from it and encapsulating
 * them in a Kotlin Result. If there is a different exception thrown, this function lets it
 * propagate up.
 */
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

/**
 * Runs the given function catching common network exceptions thrown from it.
 */
suspend fun<T> runCatchingNetworkExceptions(action: suspend () -> T): Result<T> = runCatchingExceptionsSuspend<T>(
    HttpException::class,
    SocketTimeoutException::class,
    UnknownHostException::class,
) {
    action()
}