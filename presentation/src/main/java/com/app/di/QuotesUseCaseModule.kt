package com.app.di

import com.app.common.di.RetrofitInstance
import com.app.domains.repository.quotes.QuoteRepository
import com.app.domains.usecase.quotes.QuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Handles the Quotes DI
 */
@InstallIn(SingletonComponent::class)
@Module
class QuotesUseCaseModule {

    @Provides
    fun getQuotesUseCase(@RetrofitInstance repository: QuoteRepository): QuotesUseCase =
        QuotesUseCase(repository)
}