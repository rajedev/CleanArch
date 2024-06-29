package com.app.presentation.pokemon_details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.common.utils.UiEvents
import com.app.domains.usecase.pokemon.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * View model for the pokemon details
 */
@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val pokemonDetailUseCase: GetPokemonDetailUseCase) :
    ViewModel() {

    fun getPokemonDetails() {
        viewModelScope.launch {
            pokemonDetailUseCase("1")
                .collectLatest {
                    when (it) {
                        is UiEvents.Loading -> {
                            Log.d("Pokemon Details", "Get Pokemon Details Loading")
                        }

                        is UiEvents.Success -> {
                            Log.d(
                                "Pokemon Details",
                                "Get Pokemon Details Success ${it.data?.name ?: "No Name"}"
                            )
                        }

                        is UiEvents.Error -> {
                            Log.d("Pokemon Details", "Get Pokemon Details Error ${it.message}")
                        }
                    }
                }
        }
    }
}
