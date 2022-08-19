package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Rocket
import javax.inject.Inject

interface RocketsCacheStorage {
    fun getAll(): Result<List<Rocket>?>
    fun save(rockets: List<Rocket>): Result<Unit>
}

class RocketsCacheStorageImpl @Inject constructor(
    private val fileCacheStorage: FileCacheStorage<List<Rocket>>
): RocketsCacheStorage {

    override fun getAll(): Result<List<Rocket>?> {
        return fileCacheStorage.get(fileName = CACHE_FILE_NAME)
    }

    override fun save(rockets: List<Rocket>): Result<Unit> {
        return fileCacheStorage.save(rockets, fileName = CACHE_FILE_NAME)
    }

    companion object {
        private const val CACHE_FILE_NAME = "rockets-cache.json"
    }
}