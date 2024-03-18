package com.example.tp02.member.api

import com.example.tp02.member.dto.MemberDTO

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberApiService {

    @POST("/api/member/login")
    suspend fun loginApi(@Body memberDTO: MemberDTO): Response<Map<String, Object>>

}
