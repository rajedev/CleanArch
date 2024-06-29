package com.app.domains.repository.feed

import com.app.domains.model.feed.Comment

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Feed - Get list of comments on the post id
 */
interface CommentRepository {
    suspend fun getComments(postId: String): List<Comment>
}