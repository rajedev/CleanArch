package com.app.domains.usecase.feed

import com.app.common.utils.UiEvents
import com.app.domains.model.feed.Comment
import com.app.domains.repository.feed.CommentRepository
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
 * Handle Feed comments
 */
class FeedCommentsUseCase @Inject constructor(private val commentRepository: CommentRepository) {
    operator fun invoke(postId: String): Flow<UiEvents<List<Comment>>> = flow {
        emit(UiEvents.Loading())
        emit(UiEvents.Success(commentRepository.getComments(postId)))
    }.catch {
        emit(UiEvents.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
