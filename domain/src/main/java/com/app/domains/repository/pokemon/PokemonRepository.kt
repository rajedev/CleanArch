package com.app.domains.repository.pokemon

import com.app.domain.model.pokemon.Pokemon

/**
 * Wish
 * Created by Rajendhiran Easu on 01/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Pokemon Repository to handle List and Feeds related things.
 */
interface PokemonRepository {
    suspend fun getPokemonList(): List<Pokemon>
}
