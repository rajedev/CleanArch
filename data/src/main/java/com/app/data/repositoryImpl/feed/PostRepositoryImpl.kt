package com.app.data.repositoryImpl.feed

import com.app.data.dto.feed.toDomain
import com.app.data.dto.feed.toPostRequestEntity
import com.app.data.provider.FeedAPIService
import com.app.domains.model.feed.Post
import com.app.domains.model.feed.PostRequest
import com.app.domains.repository.feed.PostRepository
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Create and Get Posts
 */
class PostRepositoryImpl @Inject constructor(private val feedAPIService: FeedAPIService) :
    PostRepository {

    override suspend fun createPost(post: PostRequest): Post {
        return feedAPIService.createPost(post.toPostRequestEntity()).toDomain()
    }

    override suspend fun getPosts(): List<Post> {
        return feedAPIService.getPosts().map {
            it.toDomain()
        }
    }
}
