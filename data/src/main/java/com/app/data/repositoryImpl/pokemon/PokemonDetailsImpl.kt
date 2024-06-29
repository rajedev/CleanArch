package com.app.data.repositoryImpl.pokemon

import com.app.data.dto.pokemon_details.toDomain
import com.app.data.provider.PokemonProvider
import com.app.domains.model.pokemon.PokemonDetails
import com.app.domains.repository.pokemon.PokemonDetailRepository
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 01/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Implementation related to the pokemon details
 */
class PokemonDetailsImpl @Inject constructor(private val provider: PokemonProvider) :
    PokemonDetailRepository {
    override suspend fun getPokemonDetail(id: String): PokemonDetails {
        return provider.getPokemonDetails(id).toDomain()
    }
}
