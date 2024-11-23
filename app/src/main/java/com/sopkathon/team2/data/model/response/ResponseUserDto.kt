package com.sopkathon.team2.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    @SerialName("nickName")
    val nickName: String,
    @SerialName("level")
    val level: Int
)

