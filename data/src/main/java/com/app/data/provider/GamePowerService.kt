package com.app.data.provider

import com.app.data.dto.games.GamesDto
import com.app.network.ALL_GAMES
import retrofit2.http.GET

/**
 * Wish
 * Created by Rajendhiran Easu on 11/07/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This will handle the game power api
 */
interface GamePowerService {
    @GET(ALL_GAMES)
    suspend fun getAllGames(): List<GamesDto>
}
