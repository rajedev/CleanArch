package com.app.data.dto.pokemon_details

import com.app.domains.model.pokemon.PokemonDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailsDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("height") val height: Int,
    @SerialName("weight") val weight: Int,
    //@SerialName("types") val types: List<Type>,
)

fun PokemonDetailsDto.toDomain(): PokemonDetails {
    return PokemonDetails(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        //type = this.types.map { it.type.name }
    )
}