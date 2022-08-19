package com.github.janruz.spacexapp.di

import com.github.janruz.spacexapp.data.local.CompanyCacheStorage
import com.github.janruz.spacexapp.data.local.RocketsCacheStorage
import com.github.janruz.spacexapp.data.networking.CompanyWebService
import com.github.janruz.spacexapp.data.networking.RocketsWebService
import com.github.janruz.spacexapp.data.repositories.CompanyRepository
import com.github.janruz.spacexapp.data.repositories.CompanyRepositoryImpl
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

    @Binds
    abstract fun bindCompanyRepository(
        companyRepositoryImpl: CompanyRepositoryImpl
    ): CompanyRepository
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesProvisionModule {

    @Provides
    @Singleton
    fun provideRocketsRepositoryImpl(
        rocketsWebService: RocketsWebService,
        rocketsCacheStorage: RocketsCacheStorage
    ): RocketsRepositoryImpl {
        return RocketsRepositoryImpl(rocketsWebService, rocketsCacheStorage)
    }

    @Provides
    @Singleton
    fun provideCompanyRepositoryImpl(
        companyWebService: CompanyWebService,
        companyCacheStorage: CompanyCacheStorage
    ): CompanyRepositoryImpl {
        return CompanyRepositoryImpl(companyWebService, companyCacheStorage)
    }
}