package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Rocket

class FakeRocketsCacheStorage: FileCacheStorage<List<Rocket>> {

    var passedFileName: String? = null
        private set

    var savedRockets: List<Rocket>? = null
        private set

    var exception: Throwable? = null

    override fun get(fileName: String): Result<List<Rocket>?> {
        exception?.let {
            return Result.failure(it)
        }

        passedFileName = fileName
        return Result.success(savedRockets)
    }

    override fun save(data: List<Rocket>, fileName: String): Result<Unit> {
        exception?.let {
            return Result.failure(it)
        }

        savedRockets = data
        passedFileName = fileName
        return Result.success(Unit)
    }
}