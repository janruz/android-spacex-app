package com.github.janruz.spacexapp.di

import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.data.models.Rocket
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Defines functions for constructing JsonAdapters for various types.
 */
@Module
@InstallIn(SingletonComponent::class)
object JsonAdaptersProvisionModule {

    @Provides
    @Singleton
    fun provideListOfRocketsJsonAdapter(moshi: Moshi): JsonAdapter<List<Rocket>> {
        val type = Types.newParameterizedType(List::class.java, Rocket::class.java)

        return moshi.adapter(type)
    }

    @Provides
    @Singleton
    fun provideCompanyJsonAdapter(moshi: Moshi): JsonAdapter<Company> {
        return moshi.adapter(Company::class.java)
    }
}