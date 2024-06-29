package com.app.data.dto.feed

import com.app.domains.model.feed.Comment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentDto(
    @SerialName("body") val body: String,
    @SerialName("email") val email: String,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("postId") val postId: Int
)

fun CommentDto.toDomain(): Comment {
    return Comment(body, email, id, name, postId)
}