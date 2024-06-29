package com.app.presentation.games

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.common.utils.UiEvents
import com.app.domains.usecase.games.GamesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * ViewModel to handles the games list
 */
@HiltViewModel
class GameViewModel @Inject constructor(private val gamesListUseCase: GamesListUseCase) :
    ViewModel() {

    fun getGameListData() {
        viewModelScope.launch {
            gamesListUseCase()
                .collectLatest {
                    when (it) {
                        is UiEvents.Loading -> {
                            Log.d("Game", "Game list Loading")
                        }

                        is UiEvents.Success -> {
                            Log.d(
                                "Game",
                                "Get Game List Success ${it.data?.get(0)?.title ?: "No Data"}"
                            )
                        }

                        is UiEvents.Error -> {
                            Log.d("Game", "Get Game List Error ${it.message}")
                        }
                    }
                }
        }
    }
}