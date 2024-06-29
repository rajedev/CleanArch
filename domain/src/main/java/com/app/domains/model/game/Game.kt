package com.app.domains.model.game

/**
 * Wish
 * Created by Rajendhiran Easu on 07/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Game Model object
 */
data class Game(
    var id: Int? = null,
    var title: String? = null,
    var thumbnail: String? = null,
    var shortDescription: String? = null,
    var gameUrl: String? = null,
    var genre: String? = null,
    var platform: String? = null,
    var publisher: String? = null,
    var developer: String? = null,
    var releaseDate: String? = null,
    var freetogameProfileUrl: String? = null
)
