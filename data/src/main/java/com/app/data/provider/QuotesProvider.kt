package com.app.data.provider

import com.app.data.dto.quotes.QuotesDto
import com.app.network.QUOTES_RANDOM
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Quotes Provider -- KtoR
 */
class QuotesProvider @Inject constructor(private val httpClient: HttpClient) {

    suspend fun getQuotes(noOfQuotes: Int) = httpClient.get {
        url(QUOTES_RANDOM)
        parameter("limit", noOfQuotes)
    }.body<List<QuotesDto>>()

}
