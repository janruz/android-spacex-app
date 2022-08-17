package com.github.janruz.spacexapp.data.networking

import com.github.janruz.spacexapp.data.models.RocketFromApi
import retrofit2.http.GET

interface RocketsWebService {

    @GET("/v4/rockets")
    suspend fun getAllRockets(): List<RocketFromApi>
}