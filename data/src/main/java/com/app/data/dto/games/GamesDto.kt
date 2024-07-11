package com.app.data.dto.games

import com.app.domains.model.game.Games
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesDto(
  @SerialName("id") var id: Int? = null,
  @SerialName("title") var title: String? = null,
  @SerialName("worth") var worth: String? = null,
  @SerialName("thumbnail") var thumbnail: String? = null,
  @SerialName("image") var image: String? = null,
  @SerialName("description") var description: String? = null,
  @SerialName("instructions") var instructions: String? = null,
  @SerialName("open_giveaway_url") var openGiveawayUrl: String? = null,
  @SerialName("published_date") var publishedDate: String? = null,
  @SerialName("type") var type: String? = null,
  @SerialName("platforms") var platforms: String? = null,
  @SerialName("end_date") var endDate: String? = null,
  @SerialName("users") var users: Int? = null,
  @SerialName("status") var status: String? = null,
  @SerialName("gamerpower_url") var gamerpowerUrl: String? = null,
  @SerialName("open_giveaway") var openGiveaway: String? = null
)

fun GamesDto.toDomain() = Games(
  gameId = id,
  gameTitle = title,
  price = worth,
  thumbnailUrl = thumbnail,
  bannerImageUrl = image,
  gameDescription = description,
  gameUrl = gamerpowerUrl
)