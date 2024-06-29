package com.app.data.provider

import com.app.data.dto.quotes.QuotesDto
import com.app.network.QUOTES_RANDOM
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This will handle the quotes api
 */
interface QuotesAPIService {

    @GET(QUOTES_RANDOM)
    suspend fun getQuotes(@Query("limit") noOfQuotes: Int): List<QuotesDto>
}
