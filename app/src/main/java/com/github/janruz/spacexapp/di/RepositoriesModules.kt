package com.github.janruz.spacexapp.di

import com.github.janruz.spacexapp.data.local.RocketsLocalStorage
import com.github.janruz.spacexapp.data.networking.RocketsWebService
import com.github.janruz.spacexapp.data.repositories.RocketsRepository
import com.github.janruz.spacexapp.data.repositories.RocketsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesBindingsModule {

    @Binds
    abstract fun bindRocketsRepository(
        rockersRepositoryImpl: RocketsRepositoryImpl
    ): RocketsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesProvisionModule {

    @Provides
    @Singleton
    fun provideRocketsRepositoryImpl(
        rocketsWebService: RocketsWebService,
        rocketsLocalStorage: RocketsLocalStorage
    ): RocketsRepositoryImpl {
        return RocketsRepositoryImpl(rocketsWebService, rocketsLocalStorage)
    }
}