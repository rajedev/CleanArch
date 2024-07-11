package com.app.data.repositoryImpl.game

import com.app.data.dto.games.toDomain
import com.app.data.provider.GamePowerService
import com.app.domains.model.game.Games
import com.app.domains.repository.games.GamePowerRepository
import javax.inject.Inject

class GamePowerImpl @Inject constructor(private val gamePowerService: GamePowerService) :
    GamePowerRepository {
    override suspend fun getPowerGameList(): List<Games> {
        return gamePowerService.getAllGames().map { it.toDomain() }
    }
}
