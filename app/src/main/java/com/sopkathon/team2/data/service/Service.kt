package com.sopkathon.team2.data.service

import com.sopkathon.team2.data.model.response.ResponseDummyDto
import retrofit2.http.POST

interface Service {
    @POST("/dummy")
    suspend fun loadPotatoes(id: Int): List<ResponseDummyDto>
}