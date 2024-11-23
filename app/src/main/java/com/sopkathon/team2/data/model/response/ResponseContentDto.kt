package com.sopkathon.team2.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseContentDto(
    val status: Int,
    val message: String,
    val data: ContentData
)

@Serializable
data class ContentData(
    val boardId: Int
)
