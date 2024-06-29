package com.app.domains.usecase.pokemon

import com.app.common.utils.UiEvents
import com.app.domains.repository.pokemon.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 01/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Get Pokemon list
 */

class GetPokemonListUseCase @Inject constructor(private val repository: PokemonRepository) {

    operator fun invoke() = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(repository.getPokemonList()))
    }
        .catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}