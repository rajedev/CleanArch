package com.app.data.repositoryImpl.quotes

import com.app.data.dto.quotes.toDomain
import com.app.data.provider.QuotesAPIService
import com.app.domains.model.quotes.Quotes
import com.app.domains.repository.quotes.QuoteRepository
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This handles the implementations of Quotes
 */
class QuotesImpl @Inject constructor(private val provider: QuotesAPIService) : QuoteRepository {
    override suspend fun getQuotes(noOfQuotes: Int): List<Quotes> {
        return provider.getQuotes(noOfQuotes).map { it.toDomain() }
    }
}
