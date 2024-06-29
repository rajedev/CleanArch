package com.app.data.dto.feed

import com.app.domains.model.feed.PostRequest
import kotlinx.serialization.Serializable

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Post Request - Object
 */
@Serializable
data class PostRequestEntity(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

fun PostRequest.toPostRequestEntity() = PostRequestEntity(body, id, title, userId)

