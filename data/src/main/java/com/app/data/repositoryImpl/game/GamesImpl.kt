package com.app.data.repositoryImpl.game

import com.app.data.dto.games.toDomain
import com.app.data.provider.GameAPIService
import com.app.domains.model.game.Game
import com.app.domains.repository.games.GamesRepository
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This take care of the Game implementations
 */
class GamesImpl @Inject constructor(private val gameAPIService: GameAPIService) : GamesRepository {
    override suspend fun getGameList(): List<Game> {
        return gameAPIService.getGameList().map {
            it.toDomain()
        }
    }
}
