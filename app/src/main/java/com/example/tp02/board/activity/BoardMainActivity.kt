package com.example.tp02.board.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp02.R
import com.example.tp02.board.adapter.BoardAdapter
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.databinding.ActivityBoardmainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardMainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBoardmainBinding
    private lateinit var boardViewModel : BoardViewModel
    private lateinit var boardAdapter: BoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boardmain)

        //RecyclerView 설정
        binding.recyclerViewBoard.layoutManager = LinearLayoutManager(this)
        boardAdapter = BoardAdapter()
        binding.recyclerViewBoard.adapter = boardAdapter

        // Retrofit 클라이언트 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val boardApiService = retrofit.create(BoardApiService::class.java)
        // BoardViewModel 초기화
        boardViewModel = BoardViewModel(boardApiService)

        boardViewModel.boardData.observe(this, Observer { boardList ->
            boardAdapter.submitList(boardList)
        })

        boardViewModel.getBoards()
    }
}