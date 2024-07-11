package com.app.domains.repository.games

import com.app.domains.model.game.Games

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Repo for the Power Games
 */

interface GamePowerRepository {
    suspend fun getPowerGameList(): List<Games>
}
