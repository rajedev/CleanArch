package com.app.data.repositoryImpl.pokemon

import com.app.data.provider.PokemonProvider
import com.app.domain.model.pokemon.Pokemon
import com.app.domains.repository.pokemon.PokemonRepository
import com.app.data.dto.pokemon_list.toDomainPokemonList
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 01/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Implementation towards the pokemon list
 */
class PokemonListImpl @Inject constructor(private val provider: PokemonProvider) :
    PokemonRepository {

    override suspend fun getPokemonList(): List<Pokemon> {
        return provider.getPokemonList().toDomainPokemonList()
    }
}