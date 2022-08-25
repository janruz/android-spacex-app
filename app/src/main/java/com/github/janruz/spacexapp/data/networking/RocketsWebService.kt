package com.github.janruz.spacexapp.data.networking

import com.github.janruz.spacexapp.data.models.RocketFromApi
import retrofit2.http.GET

/**
 * The web service for handling all the rockets related API requests.
 */
interface RocketsWebService {

    @GET("/v4/rockets")
    suspend fun getAllRockets(): List<RocketFromApi>
}