package com.app.domains.usecase.quotes

import com.app.common.di.RetrofitInstance
import com.app.common.utils.UiEvents
import com.app.domains.model.quotes.Quotes
import com.app.domains.repository.quotes.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This handles the Quotes Use Cases
 */
class QuotesUseCase @Inject constructor(@RetrofitInstance private val quoteRepository: QuoteRepository) {

    operator fun invoke(noOfQuotes  :Int) : Flow<UiEvents<List<Quotes>>> = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(quoteRepository.getQuotes(noOfQuotes)))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    fun invoke1(noOfQuotes:Int) : Flow<List<Quotes>> = flow {
        emit(quoteRepository.getQuotes(noOfQuotes))
    }.flowOn(Dispatchers.IO)
}
