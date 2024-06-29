package com.app.presentation.pokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.common.utils.UiEvents
import com.app.domains.usecase.pokemon.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Handel Pokemon list and feed
 */
@HiltViewModel
class PokemonViewModel @Inject constructor(private val pokemonListUseCase: GetPokemonListUseCase) :
    ViewModel() {

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonListUseCase()
                .collectLatest {
                    when (it) {
                        is UiEvents.Loading -> {
                            Log.d("Pokemon List", "Get Pokemon List Loading")
                        }

                        is UiEvents.Success -> {
                            Log.d(
                                "Pokemon List",
                                "Get Pokemon List Success ${it.data?.get(0) ?: "No Data"}"
                            )
                        }

                        is UiEvents.Error -> {
                            Log.d("Pokemon List", "Get Pokemon List Error ${it.message}")
                        }
                    }
                }
        }
    }
}