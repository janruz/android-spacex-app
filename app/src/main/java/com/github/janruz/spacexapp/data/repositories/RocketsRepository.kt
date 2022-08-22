package com.github.janruz.spacexapp.data.repositories

import com.github.janruz.spacexapp.data.local.RocketsCacheStorage
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.models.asRockets
import com.github.janruz.spacexapp.data.networking.RocketsWebService
import com.github.janruz.spacexapp.utilities.runCatchingNetworkExceptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RocketsRepository {
    suspend fun getRocketsFromCache(): Result<List<Rocket>?>
    suspend fun fetchRockets(): Result<List<Rocket>>
}

class RocketsRepositoryImpl @Inject constructor(
    private val rocketsWebService: RocketsWebService,
    private val rocketsLocalStorage: RocketsCacheStorage
) : RocketsRepository {

    override suspend fun getRocketsFromCache(): Result<List<Rocket>?> = withContext(Dispatchers.IO) {
        rocketsLocalStorage.getAll()
    }

    override suspend fun fetchRockets(): Result<List<Rocket>> = runCatchingNetworkExceptions {
        val rockets = rocketsWebService.getAllRockets().asRockets
        rocketsLocalStorage.save(rockets)
        rockets
    }
}