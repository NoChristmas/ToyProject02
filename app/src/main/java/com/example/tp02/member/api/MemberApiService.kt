package com.example.tp02.member.api

import com.example.tp02.member.dto.MemberDTO

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MemberApiService {

    @POST("/api/member/login")
    suspend fun loginApi(@Body memberDTO: MemberDTO): Response<Map<String, Any>>

    @GET("/api/member/duplication")
    suspend fun checkIdDuplicationApi(@Query("ur_id") ur_id : String): Response<Map<String,Any>>

    @POST("/api/member/register")
    suspend fun registerMember(@Body memberDTO: MemberDTO):Response<Map<String, Any>>
}
