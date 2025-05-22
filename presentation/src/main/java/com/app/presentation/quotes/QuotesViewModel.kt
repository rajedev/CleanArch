package com.app.presentation.quotes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.common.utils.UiEvents
import com.app.domains.model.quotes.Quotes
import com.app.domains.usecase.quotes.QuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This is a viewmodel class for the quotes
 */
@HiltViewModel
class QuotesViewModel @Inject constructor(private val quotesUseCase: QuotesUseCase) : ViewModel() {

    var quotesData = MutableStateFlow<UiEvents<List<Quotes>>>(UiEvents.Loading())

    fun getQuotesInfo() {
        quotesData.tryEmit(UiEvents.Loading())
        quotesUseCase.invoke1(3).onEach {
            Log.d("Quotes", "VM Quotes list: $it")
            quotesData.tryEmit(UiEvents.Success(it))
        }.catch {
            Log.d("Quotes", "VM Quotes Failure: ${it.message}")
            quotesData.tryEmit(UiEvents.Error(it.message.toString()))
        }.launchIn(viewModelScope)
    }

    fun getQuotesRandom() {
        quotesUseCase(3).onEach {
            quotesData.tryEmit(it)
        }.launchIn(viewModelScope)
    }

    fun getQuotes() {
        viewModelScope.launch {
            quotesUseCase(3).onEach {
                quotesData.tryEmit(it)
                when (it) {
                    is UiEvents.Loading -> {
                        Log.d("Quotes", "Quotes list Loading")
                    }

                    is UiEvents.Success -> {
                        Log.d("Quotes", "Quotes Success: ${it.data}")
                    }

                    is UiEvents.Error -> {
                        Log.d("Quotes", "Quotes Failure: ${it.message}")
                    }
                }
            }.launchIn(this)
        }
    }
}
