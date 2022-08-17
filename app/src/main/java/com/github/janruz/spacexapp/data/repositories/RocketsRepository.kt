package com.github.janruz.spacexapp.data.repositories

import com.github.janruz.spacexapp.data.mockRockets
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.models.asRockets
import com.github.janruz.spacexapp.data.networking.RocketsWebService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RocketsRepository {
    val allRockets: Flow<List<Rocket>>
}

class RocketsRepositoryImpl @Inject constructor(
    private val rocketsWebService: RocketsWebService
) : RocketsRepository {

    override val allRockets: Flow<List<Rocket>>
        get() = flow {
            emit(mockRockets)
            delay(5_000)

            while(true) {
                emit(rocketsWebService.getAllRockets().asRockets)

                delay(5_000)
            }
        }
}