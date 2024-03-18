package com.example.tp02.member.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp02.databinding.ActivityRegisterBinding
import com.example.tp02.member.viewmodel.MemberViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var memberViewModel: MemberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}