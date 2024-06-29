package com.app.domains.repository.feed

import com.app.domains.model.feed.Post
import com.app.domains.model.feed.PostRequest

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Feed - Create and Get Posts
 */
interface PostRepository {
    suspend fun createPost(post: PostRequest) : Post
    suspend fun getPosts(): List<Post>
}
