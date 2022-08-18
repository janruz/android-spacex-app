package com.github.janruz.spacexapp.di

import android.content.Context
import com.github.janruz.spacexapp.data.local.RocketsLocalStorage
import com.github.janruz.spacexapp.data.local.RocketsLocalStorageImpl
import com.github.janruz.spacexapp.data.models.Rocket
import com.squareup.moshi.JsonAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalStorageBindingsModule {

    @Binds
    abstract fun bindLocalStorage(
        localStorageImpl: RocketsLocalStorageImpl
    ): RocketsLocalStorage
}

@Module
@InstallIn(SingletonComponent::class)
object LocalStorageProvisionModule {

    @Provides
    @Singleton
    fun provideRocketsLocalStorageImpl(
        @ApplicationContext context: Context,
        adapter: JsonAdapter<List<Rocket>>
    ): RocketsLocalStorageImpl {
        return RocketsLocalStorageImpl(context, adapter)
    }
}