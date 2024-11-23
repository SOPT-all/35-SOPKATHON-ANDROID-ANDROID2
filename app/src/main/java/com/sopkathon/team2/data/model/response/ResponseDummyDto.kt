package com.sopkathon.team2.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDummyDto(
    @SerialName("dummy")
    val dummy: String
)
