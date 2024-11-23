package com.sopkathon.team2.data.service

import com.sopkathon.team2.data.model.response.ResponseContentDto
import com.sopkathon.team2.data.model.response.ResponseDummyDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Service {
    @POST("/dummy")
    suspend fun loadPotatoes(id: Int): List<ResponseDummyDto>

    @POST("/board/{userId}")
    suspend fun postContent(
        @Path("userId") userId: Int,
        @Body content: String
    ): ResponseContentDto
}
