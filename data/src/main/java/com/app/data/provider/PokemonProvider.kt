package com.app.data.provider

import com.app.data.dto.pokemon_details.PokemonDetailsDto
import com.app.data.dto.pokemon_list.PokemonListResponse
import com.app.network.POKEMON_END_POINT
import com.app.network.POKEMON_LIST
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 01/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This is a pokemon provider class were it helps to execute api calls
 */
class PokemonProvider @Inject constructor(private val httpClient: HttpClient) {

    suspend fun getPokemonList() = httpClient.get {
        url(POKEMON_LIST)
    }.body<PokemonListResponse>()

    suspend fun getPokemonDetails(id: String) = httpClient.get {
        url("${POKEMON_END_POINT}/$id")
    }.body<PokemonDetailsDto>()
}