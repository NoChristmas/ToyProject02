package com.example.tp02.board.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.databinding.ActivityBoardmainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardMainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBoardmainBinding
    private lateinit var boardViewModel : BoardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boardmain)

        // Retrofit 클라이언트 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val boardApiService = retrofit.create(BoardApiService::class.java)
        // BoardViewModel 초기화
        boardViewModel = BoardViewModel(boardApiService)
        /* Adapter 제작 필요
        //RecyclerView설정 가져와야함
        boardAdapter = BoardAdapter()
        
        boardViewModel.boardData.observe(this, Observer { boardList ->
            boardAdapter.submitList(boardList)
        })
        */

        boardViewModel.getBoards()
    }
}