package com.app.domains.repository.games

import com.app.domains.model.game.Game

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Repo for the Games
 */
interface GamesRepository {
    suspend fun getGameList():List<Game>
}
