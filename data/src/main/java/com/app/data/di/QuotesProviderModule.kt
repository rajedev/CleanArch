package com.app.data.di

import com.app.common.di.KtoRInstance
import com.app.common.di.RetrofitInstance
import com.app.data.provider.QuotesAPIService
import com.app.data.provider.QuotesProvider
import com.app.data.repositoryImpl.quotes.QuotesImpl
import com.app.data.repositoryImpl.quotes.QuotesImplWithKtorProviderImpl
import com.app.domains.repository.quotes.QuoteRepository
import com.app.network.QUOTES_END_POINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import retrofit2.Retrofit.Builder
import javax.inject.Named

/**
 * Wish
 * Created by Rajendhiran Easu on 11/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This acts as for the quotes provider
 */
@InstallIn(SingletonComponent::class)
@Module
class QuotesProviderModule {

    private fun getRetrofitBuilder(retrofitBuilder: Builder) =
        retrofitBuilder.baseUrl(QUOTES_END_POINT).build()

    @Provides
    fun getQuotesAPIService(retrofitBuilder: Builder): QuotesAPIService {
        return getRetrofitBuilder(retrofitBuilder).create(QuotesAPIService::class.java)
    }

    @Provides
    @RetrofitInstance
    //@Named("quotes_retrofit_provider")
    fun getQuotesRepository(apiService: QuotesAPIService): QuoteRepository {
        return QuotesImpl(apiService)
    }

    @Provides
    fun getQuotesProvider(httpClient: HttpClient) = QuotesProvider(httpClient)

    @Provides
    @KtoRInstance
    //@Named("quotes_ktor_provider")
    fun getQuotesRepo(quotesProvider: QuotesProvider): QuoteRepository =
        QuotesImplWithKtorProviderImpl(quotesProvider)
}
