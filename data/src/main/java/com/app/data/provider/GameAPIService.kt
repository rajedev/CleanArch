package com.app.data.provider

import com.app.data.dto.games.GameDto
import com.app.network.GAME_LIST
import retrofit2.http.GET

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This class handles the Game related api services
 */
interface GameAPIService {

    @GET(GAME_LIST)
    suspend fun getGameList(): List<GameDto>
}
