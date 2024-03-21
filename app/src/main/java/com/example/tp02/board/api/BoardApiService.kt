package com.example.tp02.board.api

import com.example.tp02.board.dto.BoardDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface BoardApiService {
    @GET("/api/board")
    suspend fun getBoardsApi(): Response<Map<String,Object>>

    /*
    @PUT("/api/board/{bd_no}")
    suspend fun loginApi(@Body memberDTO: MemberDTO): Response<Map<String, Object>>
    */



    /*
    @POST("/api/member/register")
    suspend fun registerMember(@Body memberDTO: MemberDTO): Response<Map<String, Object>>

     */
}
