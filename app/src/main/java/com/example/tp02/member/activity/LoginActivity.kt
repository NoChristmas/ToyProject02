package com.example.tp02.member.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.databinding.ActivityLoginBinding
import com.example.tp02.member.api.MemberApiService
import com.example.tp02.member.viewmodel.LoginViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//controller
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) { // 맨처음 동작
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        
        // Retrofit 클라이언트 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // MemberApiService 인스턴스 생성
        val memberApiService = retrofit.create(MemberApiService::class.java)

        // LoginViewModel 인스턴스 생성 및 MemberApiService 주입
        loginViewModel = LoginViewModel(memberApiService)

        // DataBinding 설정
        binding.loginViewModel = loginViewModel

        // 로그인 결과를 관찰하여 UI 업데이트
        loginViewModel.loginResult.observe(this, Observer { success ->
            if (success) {
                showToast("로그인 성공")
            } else {
                showToast("로그인 실패")
            }
        })
        // 로그인 버튼 클릭 이벤트 처리

    }

    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}