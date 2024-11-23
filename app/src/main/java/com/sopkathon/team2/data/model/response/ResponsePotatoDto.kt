package com.sopkathon.team2.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePotatoDto(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("content")
    val content: String,
    @SerialName("image")
    val image: String,
)

@Serializable
data class ResponseWrapper<T>(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: T
)
