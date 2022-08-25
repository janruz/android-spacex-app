package com.github.janruz.spacexapp.data.networking

import com.github.janruz.spacexapp.data.models.CompanyFromApi
import retrofit2.http.GET

/**
 * The web service for handling all the company related API requests.
 */
interface CompanyWebService {

    @GET("/v4/company")
    suspend fun getCompanyInfo(): CompanyFromApi
}