package com.app.di

import com.app.domains.repository.games.GamePowerRepository
import com.app.domains.repository.games.GamesRepository
import com.app.domains.usecase.games.GamePowerUseCase
import com.app.domains.usecase.games.GamesListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Handles the DI for Game repo
 */
@InstallIn(SingletonComponent::class)
@Module
class GameUseCaseModule {

    @Provides
    fun providesGameUseCase(gamesRepository: GamesRepository): GamesListUseCase {
        return GamesListUseCase(gamesRepository)
    }

    @Provides
    fun providesGamePowerUseCase(gamePowerRepository: GamePowerRepository): GamePowerUseCase {
        return GamePowerUseCase(gamePowerRepository)
    }
}
