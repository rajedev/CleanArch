package com.app.domains.usecase.games

import com.app.common.utils.UiEvents
import com.app.domains.model.game.Games
import com.app.domains.repository.games.GamePowerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 11/07/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This handles the Game power list use case
 */
class GamePowerUseCase @Inject constructor(private val gamePowerRepository: GamePowerRepository) {
    operator fun invoke(): Flow<UiEvents<List<Games>>> = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(gamePowerRepository.getPowerGameList()))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}