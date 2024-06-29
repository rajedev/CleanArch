package com.app.data.dto.quotes

import com.app.domains.model.quotes.Quotes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuotesDto(
  @SerialName("_id") var id: String,
  @SerialName("content") var content: String,
  @SerialName("author") var author: String,
  @SerialName("tags") var tags: ArrayList<String> = arrayListOf(),
  @SerialName("authorSlug") var authorSlug: String? = null,
  @SerialName("length") var length: Int? = null,
  @SerialName("dateAdded") var dateAdded: String? = null,
  @SerialName("dateModified") var dateModified: String? = null
)

fun QuotesDto.toDomain() = Quotes(id, content, author)