package com.app.data.di

import com.app.data.provider.GamePowerService
import com.app.data.repositoryImpl.game.GamePowerImpl
import com.app.domains.repository.games.GamePowerRepository
import com.app.network.POWER_GAME_END_POINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

/**
 * Wish
 * Created by Rajendhiran Easu on 11/07/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This class handles the power games providers method
 */
@Module
@InstallIn(SingletonComponent::class)
class GamePowerModule {

    private fun provideGameRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit =
        retrofitBuilder.baseUrl(POWER_GAME_END_POINT).build()

    @Provides
    fun providesGamePowerService(retrofitBuilder: Retrofit.Builder): GamePowerService {
        return provideGameRetrofit(retrofitBuilder).create(GamePowerService::class.java)
    }

    @Provides
    fun providesGameRepository(gamePowerService: GamePowerService): GamePowerRepository {
        return GamePowerImpl(gamePowerService)
    }
}
