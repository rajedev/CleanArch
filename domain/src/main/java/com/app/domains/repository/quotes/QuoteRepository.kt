package com.app.domains.repository.quotes

import com.app.domains.model.quotes.Quotes

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Quotes repository be handled here
 */
interface QuoteRepository {
    suspend fun getQuotes(noOfQuotes:Int): List<Quotes>
}