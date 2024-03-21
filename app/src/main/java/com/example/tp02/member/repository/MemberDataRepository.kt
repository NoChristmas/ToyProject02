package com.example.tp02.member.repository

import android.content.Context
import android.content.SharedPreferences

class MemberDataRepository(context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("member_prefs", Context.MODE_PRIVATE)
    }
    fun saveToken(token:String) {
        sharedPreferences.edit().putString("token",token).apply()
    }
    fun getToken(): String? {
        return sharedPreferences.getString("token","")
    }
    fun clearToken() {
        sharedPreferences.edit().remove("token").apply()
    }

}