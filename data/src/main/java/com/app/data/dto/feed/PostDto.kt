package com.app.data.dto.feed

import com.app.domains.model.feed.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    @SerialName("body") val body: String,
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("userId") val userId: Int
)

fun PostDto.toDomain(): Post {
    return Post(body, id, title, userId)
}