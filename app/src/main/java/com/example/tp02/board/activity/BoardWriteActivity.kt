package com.example.tp02.board.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.board.adapter.BoardCategoryAdapter
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.databinding.ActivityBoardwriteBinding
import com.example.tp02.interceptor.TokenInterceptor
import com.example.tp02.member.repository.MemberDataRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardwriteBinding
    private lateinit var boardViewModel: BoardViewModel
    private lateinit var boardCategoryAdapter: BoardCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boardwrite)
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
        binding.boardViewModel = boardViewModel
        boardCategoryAdapter = BoardCategoryAdapter()

        //Spinner 설정
        val spinnerData = boardCategoryAdapter.getCategoryData()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bdTypeSpinner.adapter = adapter

        boardViewModel.writeResult.observe(this, Observer {success->
            if (success) { //로그인 성공시 로직
                showToast("작성 성공")
                goBoardMainActivity()
            } else {
                showToast("작성 실패")
            }
        })
    }
    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun goBoardMainActivity() {
        startActivity(Intent(this,BoardMainActivity::class.java))
    }

}