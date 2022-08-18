package com.github.janruz.spacexapp.data.repositories

import android.accounts.NetworkErrorException
import com.github.janruz.spacexapp.data.local.RocketsLocalStorage
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.models.asRockets
import com.github.janruz.spacexapp.data.networking.RocketsWebService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

interface RocketsRepository {
    suspend fun getRocketsFromCache(): Result<List<Rocket>>
    suspend fun fetchRockets(): Result<List<Rocket>>
}

class RocketsRepositoryImpl @Inject constructor(
    private val rocketsWebService: RocketsWebService,
    private val rocketsLocalStorage: RocketsLocalStorage
) : RocketsRepository {

    override suspend fun getRocketsFromCache(): Result<List<Rocket>> = withContext(Dispatchers.IO) {
        rocketsLocalStorage.getRockets()
    }

    override suspend fun fetchRockets(): Result<List<Rocket>> = withContext(Dispatchers.IO) {
        try {
            val rockets = rocketsWebService.getAllRockets().asRockets
            rocketsLocalStorage.saveRockets(rockets)
            Result.success(rockets)
        } catch(e: Exception) {

            when(e) {
                is HttpException,
                is SocketTimeoutException,
                is UnknownHostException -> Result.failure(e)
                else -> throw e
            }
        }
    }
}