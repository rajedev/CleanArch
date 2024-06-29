package com.app.data.di

import com.app.data.provider.FeedAPIService
import com.app.data.repositoryImpl.feed.CommentsRepositoryImpl
import com.app.data.repositoryImpl.feed.PostRepositoryImpl
import com.app.domains.repository.feed.CommentRepository
import com.app.domains.repository.feed.PostRepository
import com.app.network.FEED_END_POINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This is for to handle the Feed related DI
 */
@InstallIn(SingletonComponent::class)
@Module
class FeedProvidersModule {

    private fun provideFeedRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit =
        retrofitBuilder.baseUrl(FEED_END_POINT).build()

    @Provides
    fun providesFeedService(retrofitBuilder: Retrofit.Builder): FeedAPIService =
        provideFeedRetrofit(retrofitBuilder).create(FeedAPIService::class.java)

    @Provides
    fun providesPostRepository(apiService: FeedAPIService): PostRepository =
        PostRepositoryImpl(apiService)

    @Provides
    fun providesCommentsRepository(apiService: FeedAPIService): CommentRepository =
        CommentsRepositoryImpl(apiService)
}
