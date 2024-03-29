package com.example.tp02.member.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.board.activity.BoardMainActivity
import com.example.tp02.databinding.ActivityLoginBinding
import com.example.tp02.member.api.MemberApiService
import com.example.tp02.member.repository.MemberDataRepository
import com.example.tp02.member.viewmodel.MemberViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//view
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var memberViewModel: MemberViewModel

    override fun onCreate(savedInstanceState: Bundle?) { // 맨처음 동작
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        // Retrofit 클라이언트 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // MemberApiService 인스턴스 생성
        val memberApiService = retrofit.create(MemberApiService::class.java)
        val memberDataRepository = MemberDataRepository(this)
        // LoginViewModel 인스턴스 생성 및 MemberApiService 주입
        memberViewModel = MemberViewModel(memberApiService, memberDataRepository)
        // DataBinding 설정
        binding.memberViewModel = memberViewModel
        // 로그인 결과를 관찰하여 UI 업데이트
        memberViewModel.loginResult.observe(this, Observer { success ->
            if (success) { //로그인 성공시 로직
                showToast("로그인 성공")
                goBoardMainActivity()
            } else {
                showToast("로그인 실패")
            }
        })
        binding.buttonRegister.setOnClickListener {
            goRegisterActivity()
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun goRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun goBoardMainActivity() {
        val intent = Intent(this, BoardMainActivity::class.java)
        startActivity(intent)
    }
}
