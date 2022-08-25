package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Rocket
import javax.inject.Inject

/**
 * The interface describing a local cache storage for rockets.
 */
interface RocketsCacheStorage {
    fun getAll(): Result<List<Rocket>?>
    fun save(rockets: List<Rocket>): Result<Unit>
}