package com.app.data.repositoryImpl.quotes

import com.app.data.dto.quotes.toDomain
import com.app.data.provider.QuotesProvider
import com.app.domains.model.quotes.Quotes
import com.app.domains.repository.quotes.QuoteRepository
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Quotes implementations with ktor
 */
class QuotesImplWithKtorProviderImpl @Inject constructor(private val provider: QuotesProvider) :
    QuoteRepository {
    override suspend fun getQuotes(noOfQuotes: Int): List<Quotes> {
        return provider.getQuotes(noOfQuotes).map { it.toDomain() }
    }
}
