package com.github.janruz.spacexapp.di

import com.github.janruz.spacexapp.BuildConfig
import com.github.janruz.spacexapp.data.networking.RocketsWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun provideRocketsWebService(
        retrofit: Retrofit
    ): RocketsWebService {
        return retrofit.create(RocketsWebService::class.java)
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