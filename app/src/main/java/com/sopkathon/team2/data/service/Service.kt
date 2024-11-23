package com.sopkathon.team2.data.service

import com.sopkathon.team2.data.model.response.ResponseDummyDto
import com.sopkathon.team2.data.model.response.ResponsePotatoDto
import com.sopkathon.team2.data.model.response.ResponseUserDto
import com.sopkathon.team2.data.model.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Service {
    @POST("/dummy")
    suspend fun loadPotatoes(id: Int): List<ResponseDummyDto>

    @GET("/user/{1}")
    fun getUserById(userId: Long): Response<ResponseUserDto>

    @GET("/board/{boardId}")
    suspend fun getPotatoById(@Path("boardId") boardId: Long): Response<ResponseWrapper<ResponsePotatoDto>>
}