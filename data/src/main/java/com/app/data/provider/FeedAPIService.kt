package com.app.data.provider

import com.app.data.dto.feed.CommentDto
import com.app.data.dto.feed.PostDto
import com.app.data.dto.feed.PostRequestEntity
import com.app.network.COMMENT_PATH
import com.app.network.POST_PATH
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Feed Api service
 */
interface FeedAPIService {

    @POST(POST_PATH)
    suspend fun createPost(@Body postDto: PostRequestEntity) : PostDto

    @GET(POST_PATH)
    suspend fun getPosts(): List<PostDto>


    @GET(COMMENT_PATH)
    suspend fun getComments(@Query("id") id: String): List<CommentDto>
}
