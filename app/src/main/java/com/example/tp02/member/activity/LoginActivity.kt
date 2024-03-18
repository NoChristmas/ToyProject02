package com.example.tp02.member.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tp02.R
import com.example.tp02.databinding.ActivityLoginBinding
import com.example.tp02.member.model.LoginModel
import com.example.tp02.member.viewmodel.LoginViewModel

//controller
class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    var loginViewModel: LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginActivity = this

        binding.buttonLogin.setOnClickListener {
            val userId = binding.editUserId.text.toString()
            val userPasswd = binding.editPassword.text.toString()
            clickLogin(userId, userPasswd)

        }
    }
    //로그인 함수
    fun clickLogin(userId: String, userPasswd: String) {

    }
}
