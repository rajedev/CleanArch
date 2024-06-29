package com.app.data.di

import com.app.data.provider.GameAPIService
import com.app.data.repositoryImpl.game.GamesImpl
import com.app.domains.repository.games.GamesRepository
import com.app.network.GAME_END_POINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This class handles the games providers method
 */
@Module
@InstallIn(SingletonComponent::class)
class GamesProvidersModule {

    private fun provideGameRetrofit(retrofitBuilder: Builder): Retrofit =
        retrofitBuilder.baseUrl(GAME_END_POINT).build()

    @Provides
    fun providesGamesAPIService(retrofitBuilder: Builder): GameAPIService =
        provideGameRetrofit(retrofitBuilder).create(GameAPIService::class.java)

    @Provides
    fun providesGameRepository(apiService: GameAPIService): GamesRepository = GamesImpl(apiService)
}