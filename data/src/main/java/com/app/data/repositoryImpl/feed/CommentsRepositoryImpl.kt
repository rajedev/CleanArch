package com.app.data.repositoryImpl.feed

import com.app.data.dto.feed.toDomain
import com.app.data.provider.FeedAPIService
import com.app.domains.model.feed.Comment
import com.app.domains.repository.feed.CommentRepository
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Handles the Post Comments
 */
class CommentsRepositoryImpl @Inject constructor(private val apiService: FeedAPIService) :
    CommentRepository {
    override suspend fun getComments(postId: String): List<Comment> {
        return apiService.getComments(postId).map {
            it.toDomain()
        }
    }
}