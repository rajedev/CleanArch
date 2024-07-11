package com.app.presentation.games

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.common.utils.UiEvents
import com.app.domains.usecase.games.GamePowerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamePowerViewModel @Inject constructor(private val gamePowerUseCase: GamePowerUseCase) :
    ViewModel() {

    fun getGamePowerList() {
        viewModelScope.launch {
            gamePowerUseCase().collectLatest {
                when (it) {
                    is UiEvents.Loading -> {
                        Log.d("Power Game", "Power Game list Loading")
                    }

                    is UiEvents.Success -> {
                        Log.d("Power Game", "Power Game: ${it.data?.get(0)}")
                    }

                    is UiEvents.Error -> {
                        Log.d("Power Game", "Power Game: ${it.message}")
                    }
                }
            }
        }
    }
}