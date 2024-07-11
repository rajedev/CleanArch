package com.app.network

const val TIMEOUT = 30_000L
const val POKEMON_ROOT_END_POINT = "https://pokeapi.co/api/v2/"
const val POKEMON_END_POINT = "$POKEMON_ROOT_END_POINT/pokemon/"
const val POKEMON_LIST = "$POKEMON_END_POINT?offset=0&limit=10"

const val FEED_END_POINT = "https://jsonplaceholder.typicode.com/"
const val POST_PATH = "posts"
const val COMMENT_PATH = "comments"

const val GAME_END_POINT ="https://www.freetogame.com/api/"
const val GAME_LIST = "games"

const val QUOTES_END_POINT = "https://api.quotable.io/quotes/"
const val QUOTES_RANDOM ="random"

const val POWER_GAME_END_POINT = "https://www.gamerpower.com/api/"
const val ALL_GAMES = "giveaways"
