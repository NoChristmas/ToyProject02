package com.example.tp02.member.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.databinding.ActivityRegisterBinding
import com.example.tp02.member.api.MemberApiService
import com.example.tp02.member.viewmodel.MemberViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var memberViewModel: MemberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        // Retrofit 클라이언트 생성 (API 관련 옵션)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // MemberApiService 인스턴스 생성
        val memberApiService = retrofit.create(MemberApiService::class.java)
        memberViewModel = MemberViewModel(memberApiService)
        //binding 설정
        binding.memberViewModel = memberViewModel

        memberViewModel.checkIdResult.observe(this, Observer {success ->
            if(success) {
                showToast("사용가능한 아이디입니다")
                binding.buttonSignup.isEnabled = true
                binding.buttonIdCheck.isEnabled = false
            } else {
                showToast("아이디 중복체크 실패")
                binding.buttonSignup.isEnabled = false
                binding.buttonIdCheck.isEnabled = true
            }
        })


        memberViewModel.registerResult.observe(this, Observer { success ->
            if(success) {
                showToast("회원가입 성공")
                goLoginActivity()
            } else {
                showToast("회원가입 실패")
            }
        })

    }
    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun goLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}