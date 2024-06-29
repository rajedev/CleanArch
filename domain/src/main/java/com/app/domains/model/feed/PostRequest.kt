package com.app.domains.model.feed

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Post Request - Object
 */
data class PostRequest(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)
