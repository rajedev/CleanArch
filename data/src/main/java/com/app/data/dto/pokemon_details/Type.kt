package com.app.data.dto.pokemon_details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("type") val type: TypeX
)