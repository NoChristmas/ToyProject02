package com.example.tp02.board.api

import com.example.tp02.board.dto.BoardDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardApiService {
    @GET("/api/board")
    suspend fun getBoardsApi(): Response<Map<String,Any>>
    @POST("/api/board")
    suspend fun writeBoardApi(@Body boardDTO: BoardDTO): Response<Map<String,Any>>
    @GET("/api/board/{bd_no}")
    suspend fun getBoardApi(@Path ("bd_no") bd_no: Int): Response<Map<String, Any>>
    @PUT("/api/board/{bd_no}")
    suspend fun modifyBoardApi(@Path ("bd_no") bd_no: Int ,@Body boardDTO: BoardDTO): Response<Map<String, Any>>
    @DELETE("/api/board/{bd_no}")
    suspend fun deleteBoardApi(@Path("bd_no") bd_no:Int): Response<Map<String,Any>>
}
