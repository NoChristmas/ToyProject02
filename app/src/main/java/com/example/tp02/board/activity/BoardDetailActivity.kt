package com.example.tp02.board.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.board.adapter.BoardAdapter
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.databinding.ActivityBoarddetailBinding
import com.example.tp02.interceptor.TokenInterceptor
import com.example.tp02.member.activity.LoginActivity
import com.example.tp02.member.repository.MemberDataRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardDetailActivity : AppCompatActivity() {
    private lateinit var boardViewModel : BoardViewModel
    private lateinit var binding : ActivityBoarddetailBinding
    private lateinit var boardAdapter: BoardAdapter
    private var bd_no: Int = 0
    private val boardList = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boarddetail)
        val memberDataRepository = MemberDataRepository(this)
        val token = memberDataRepository.getToken().toString()
        // OkHttpClient에 TokenInterceptor 추가
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(token))
            .build()
        // Retrofit 클라이언트 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val boardApiService = retrofit.create(BoardApiService::class.java)
        // BoardViewModel 초기화
        boardViewModel = BoardViewModel(boardApiService)

        bd_no = intent.getIntExtra("bd_no",0)
        Log.d("bd_no",bd_no.toString())
        boardViewModel.boardDetailData.observe(this, Observer { boardDetailList ->
            if(boardDetailList.isNotEmpty()) {
                val boardDetail = boardDetailList[0]
                binding.bdNo.text = "번호: ${boardDetail.bd_no}"
                binding.bdName.text = "제목: ${boardDetail.bd_name}"
                binding.bdInfo.text = "내용: ${boardDetail.bd_info}"
                binding.urName.text = "작성자: ${boardDetail.ur_name}"
                binding.bdHit.text = "조회수: ${boardDetail.bd_hit}"
                binding.bdRegDate.text = "작성날짜: ${boardDetail.bd_reg_date}"
            }

        })
        boardViewModel.getBoard(bd_no)

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