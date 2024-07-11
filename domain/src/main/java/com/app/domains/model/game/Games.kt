package com.app.domains.model.game

data class Games(
  var gameId: Int? = null,
  var gameTitle: String? = null,
  var price: String? = null,
  var thumbnailUrl: String? = null,
  var bannerImageUrl: String? = null,
  var gameDescription: String? = null,
  var gameUrl: String? = null,
)
