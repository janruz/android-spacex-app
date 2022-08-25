package com.github.janruz.spacexapp.data.local

/**
 * The interface describing a local file cache storage.
 */
interface FileCacheStorage<T> {
    fun get(fileName: String): Result<T?>
    fun save(data: T, fileName: String): Result<Unit>
}