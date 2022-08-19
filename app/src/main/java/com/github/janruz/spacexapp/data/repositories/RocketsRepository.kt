package com.github.janruz.spacexapp.data.repositories

import com.github.janruz.spacexapp.data.local.RocketsCacheStorage
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.models.asRockets
import com.github.janruz.spacexapp.data.networking.RocketsWebService
import com.github.janruz.spacexapp.data.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
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

    override suspend fun fetchRockets(): Result<List<Rocket>> = safeApiCall {
        val rockets = rocketsWebService.getAllRockets().asRockets
        rocketsLocalStorage.save(rockets)
        rockets
    }
}