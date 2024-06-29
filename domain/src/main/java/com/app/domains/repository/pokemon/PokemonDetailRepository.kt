package com.app.domains.repository.pokemon

import com.app.domains.model.pokemon.PokemonDetails

/**
 * Wish
 * Created by Rajendhiran Easu on 01/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This handles the method of Pokemon Details related
 */
interface PokemonDetailRepository {
    suspend fun getPokemonDetail(id: String): PokemonDetails
}
