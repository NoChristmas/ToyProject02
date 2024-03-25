package com.example.tp02.board.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.interceptor.TokenInterceptor
import com.example.tp02.member.repository.MemberDataRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseBoardActivity : AppCompatActivity() {
    protected lateinit var boardViewModel: BoardViewModel
    protected lateinit var memberDataRepository: MemberDataRepository
    private lateinit var boardApiService: BoardApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRetrofit()
        initBoardViewMode()
        memberDataRepository = MemberDataRepository(this)
    }

    private fun initRetrofit() {
        val memberDataRepository = MemberDataRepository(this)
        val token = memberDataRepository.getToken().toString()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(token))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        boardApiService = retrofit.create(BoardApiService::class.java)
    }
    private fun initBoardViewMode() {
        boardViewModel = BoardViewModel(boardApiService)
    }
}