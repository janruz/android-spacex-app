package com.github.janruz.spacexapp.di

import android.content.Context
import com.github.janruz.spacexapp.data.local.*
import com.github.janruz.spacexapp.data.models.Company
import com.github.janruz.spacexapp.data.models.Rocket
import com.squareup.moshi.JsonAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Defines functions binding cache storage implementations to corresponding interfaces.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class CacheStorageBindingsModule {

    @Binds
    abstract fun bindRocketsCacheStorage(
        rocketsCacheStorageImpl: RocketsFileCacheStorage
    ): RocketsCacheStorage

    @Binds
    abstract fun bindCompanyCacheStorage(
        companyCacheStorageImpl: CompanyFileCacheStorage
    ): CompanyCacheStorage

    @Binds
    abstract fun bindFileStorageForListOfRockets(
        jsonFileStorageForRockets: JsonFileCacheStorage<List<Rocket>>
    ): FileCacheStorage<List<Rocket>>

    @Binds
    abstract fun bindFileStorageForCompany(
        jsonFileStorageForRockets: JsonFileCacheStorage<Company>
    ): FileCacheStorage<Company>
}

/**
 * Defines functions for constructing instances of cache storage related components.
 */
@Module
@InstallIn(SingletonComponent::class)
object CacheStorageProvisionModule {

    @Provides
    @Singleton
    fun provideJsonFileCacheStorageForRockets(
        @ApplicationContext context: Context,
        adapter: JsonAdapter<List<Rocket>>
    ): JsonFileCacheStorage<List<Rocket>> {
        return JsonFileCacheStorage(context, adapter)
    }

    @Provides
    @Singleton
    fun provideJsonFileCacheStorageForCompany(
        @ApplicationContext context: Context,
        adapter: JsonAdapter<Company>
    ): JsonFileCacheStorage<Company> {
        return JsonFileCacheStorage(context, adapter)
    }
}