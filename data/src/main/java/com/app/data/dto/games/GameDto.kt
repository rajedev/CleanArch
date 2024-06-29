package com.app.data.dto.games

import com.app.domains.model.game.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
  @SerialName("id") var id: Int? = null,
  @SerialName("title") var title: String? = null,
  @SerialName("thumbnail") var thumbnail: String? = null,
  @SerialName("short_description") var shortDescription: String? = null,
  @SerialName("game_url") var gameUrl: String? = null,
  @SerialName("genre") var genre: String? = null,
  @SerialName("platform") var platform: String? = null,
  @SerialName("publisher") var publisher: String? = null,
  @SerialName("developer") var developer: String? = null,
  @SerialName("release_date") var releaseDate: String? = null,
  @SerialName("freetogame_profile_url") var freetogameProfileUrl: String? = null
)

fun GameDto.toDomain(): Game =
  Game(
    id,
    title,
    thumbnail,
    shortDescription,
    gameUrl,
    genre,
    platform,
    publisher,
    developer,
    releaseDate,
    freetogameProfileUrl
  )