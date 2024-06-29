package com.app.domains.usecase.games

import com.app.common.utils.UiEvents
import com.app.domains.model.game.Game
import com.app.domains.repository.games.GamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This handles the Game list use case
 */
class GamesListUseCase @Inject constructor(private val gamesRepository: GamesRepository) {

    operator fun invoke(): Flow<UiEvents<List<Game>>> = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(gamesRepository.getGameList()))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
