package com.app.data.dto.pokemon_list

import com.app.domain.model.pokemon.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("results") val results: List<PokemonDto>
)

fun PokemonListResponse.toDomainPokemonList(): List<Pokemon> {
    return this.results.map {
        Pokemon(id = it.getId(), name = it.name)
    }
}