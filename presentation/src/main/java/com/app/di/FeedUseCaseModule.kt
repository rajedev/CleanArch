package com.app.di

import com.app.domains.repository.feed.CommentRepository
import com.app.domains.repository.feed.PostRepository
import com.app.domains.usecase.feed.FeedCommentsUseCase
import com.app.domains.usecase.feed.FeedPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Provides the DI for Feed repository usecase (Post and Comments)
 */
@InstallIn(SingletonComponent::class)
@Module
class FeedUseCaseModule {

    @Provides
    fun providesFeedPostUseCase(postRepository: PostRepository) = FeedPostUseCase(postRepository)

    @Provides
    fun providesFeedCommentsUseCase(commentRepository: CommentRepository) =
        FeedCommentsUseCase(commentRepository)
}