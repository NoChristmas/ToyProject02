package com.example.tp02.board.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp02.R
import com.example.tp02.board.adapter.BoardAdapter
import com.example.tp02.board.adapter.BoardDetailAdapter
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.databinding.ActivityBoardmainBinding
import com.example.tp02.interceptor.TokenInterceptor
import com.example.tp02.member.activity.LoginActivity
import com.example.tp02.member.activity.RegisterActivity
import com.example.tp02.member.repository.MemberDataRepository
import com.example.tp02.member.viewmodel.MemberViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardMainActivity : BaseBoardActivity() {
    private lateinit var binding: ActivityBoardmainBinding
    private lateinit var boardAdapter: BoardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boardmain)
        //RecyclerView 설정
        binding.recyclerViewBoard.layoutManager = LinearLayoutManager(this)

        boardViewModel.boardData.observe(this, Observer { boardList ->
            boardAdapter = BoardAdapter(boardList) { bd_no ->
                val intent = Intent(this,BoardDetailActivity::class.java)
                intent.putExtra("bd_no", bd_no)
                startActivity(intent)
            }
            binding.recyclerViewBoard.adapter = boardAdapter
        })
        boardViewModel.getBoards()

        binding.buttonWrite.setOnClickListener {
            goBoardWriteActivity()
        }
        binding.buttonLogout.setOnClickListener {
            memberDataRepository.clearToken()
            goLoginActivity()
        }
    }
    private fun goBoardWriteActivity() {
        val intent = Intent(this, BoardWriteActivity::class.java)
        startActivity(intent)
    }
    private fun goLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}