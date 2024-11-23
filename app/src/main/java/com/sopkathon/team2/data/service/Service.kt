package com.sopkathon.team2.data.service

import com.sopkathon.team2.data.model.request.RequestContentDto
import com.sopkathon.team2.data.model.response.ResponseContentDto
import com.sopkathon.team2.data.model.response.ResponseDummyDto
import com.sopkathon.team2.data.model.response.ResponsePotatoDto
import com.sopkathon.team2.data.model.response.ResponseUserDto
import com.sopkathon.team2.data.model.response.ResponseWrapper
import com.sopkathon.team2.data.model.response.ResponseProfileDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Service {
    @POST("/dummy")
    suspend fun loadPotatoes(id: Int): List<ResponseDummyDto>

    @GET("/boards/{userId}")
    suspend fun loadProfiles(@Path("userId") userId: Long): Response<ResponseProfileDto>

    @GET("/board/{boardId}")
    suspend fun getPotatoById(@Path("boardId") boardId: Long): Response<ResponseWrapper<ResponsePotatoDto>>

    @POST("/board/{userId}")
    suspend fun postContent(
        @Path("userId") userId: Int,
        @Body requestContentDto: RequestContentDto
    ): Response<ResponseContentDto>

    @GET("/user/{userId}")
    suspend fun getUserById(@Path("userId") userId: Long): Response<ResponseWrapper<ResponseUserDto>>
}