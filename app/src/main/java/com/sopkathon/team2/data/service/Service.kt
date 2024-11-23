package com.sopkathon.team2.data.service

import com.sopkathon.team2.data.model.response.ResponseDummyDto
import com.sopkathon.team2.data.model.response.ResponseProfileDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Service {
    @POST("/dummy")
    suspend fun loadPotatoes(id: Int): List<ResponseDummyDto>

    @GET("/boards/{userId}")
    suspend fun loadProfiles(@Path("userId") userId: Long): Response<ResponseProfileDto>
}