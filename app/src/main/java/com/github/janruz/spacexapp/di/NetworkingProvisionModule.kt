package com.github.janruz.spacexapp.di

import com.github.janruz.spacexapp.BuildConfig
import com.github.janruz.spacexapp.data.models.Rocket
import com.github.janruz.spacexapp.data.networking.CompanyWebService
import com.github.janruz.spacexapp.data.networking.RocketsWebService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Defines functions for constructing instances of network related components.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkingProvisionModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRocketsWebService(
        retrofit: Retrofit
    ): RocketsWebService {
        return retrofit.create(RocketsWebService::class.java)
    }

    @Provides
    @Singleton
    fun provideCompanyWebService(
        retrofit: Retrofit
    ): CompanyWebService {
        return retrofit.create(CompanyWebService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}