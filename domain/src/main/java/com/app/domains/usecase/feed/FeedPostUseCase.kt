package com.app.domains.usecase.feed

import com.app.common.utils.UiEvents
import com.app.domains.model.feed.Post
import com.app.domains.model.feed.PostRequest
import com.app.domains.repository.feed.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Handle the feed post
 */
class FeedPostUseCase @Inject constructor(private val postRepository: PostRepository) {

    operator fun invoke(postRequest: PostRequest): Flow<UiEvents<Post>> = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(postRepository.createPost(postRequest)))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    operator fun invoke(): Flow<UiEvents<List<Post>>> = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(postRepository.getPosts()))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
