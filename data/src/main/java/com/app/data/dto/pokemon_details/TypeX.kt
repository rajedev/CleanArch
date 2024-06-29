package com.app.data.dto.pokemon_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeX(
    @SerialName("name") val name: String
)