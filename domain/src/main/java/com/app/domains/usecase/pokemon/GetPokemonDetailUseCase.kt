package com.app.domains.usecase.pokemon

import com.app.common.utils.UiEvents
import com.app.domains.model.pokemon.PokemonDetails
import com.app.domains.repository.pokemon.PokemonDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 01/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * To get the pokemon details
 */
class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonDetailRepository
) {
    operator fun invoke(id: String): Flow<UiEvents<PokemonDetails>> = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(repository.getPokemonDetail(id)))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}