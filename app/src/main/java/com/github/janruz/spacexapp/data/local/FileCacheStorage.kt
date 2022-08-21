package com.github.janruz.spacexapp.data.local

interface FileCacheStorage<T> {
    fun get(fileName: String): Result<T?>
    fun save(data: T, fileName: String): Result<Unit>
}