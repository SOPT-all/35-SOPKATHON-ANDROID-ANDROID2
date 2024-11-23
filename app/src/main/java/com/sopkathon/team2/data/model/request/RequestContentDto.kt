package com.sopkathon.team2.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestContentDto(

    @SerialName("content")
    val content: String
)
