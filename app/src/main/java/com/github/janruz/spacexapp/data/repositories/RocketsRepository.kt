package com.github.janruz.spacexapp.data.repositories

import com.github.janruz.spacexapp.data.mockRockets
import com.github.janruz.spacexapp.models.Rocket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface RocketsRepository {
    val allRockets: Flow<List<Rocket>>
}

class RocketsRepositoryImpl : RocketsRepository {

    override val allRockets: Flow<List<Rocket>>
        get() = flowOf(mockRockets)
}