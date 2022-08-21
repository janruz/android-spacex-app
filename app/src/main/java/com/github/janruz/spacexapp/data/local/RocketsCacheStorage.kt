package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Rocket
import javax.inject.Inject

interface RocketsCacheStorage {
    fun getAll(): Result<List<Rocket>?>
    fun save(rockets: List<Rocket>): Result<Unit>
}