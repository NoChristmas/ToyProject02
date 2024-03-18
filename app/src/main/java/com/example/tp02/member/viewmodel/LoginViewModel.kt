package com.example.tp02.member.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tp02.member.api.MemberApiService
import com.example.tp02.member.dto.MemberDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//Service같은 놈
class LoginViewModel(private val memberApiService: MemberApiService) {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>get() = _loginResult

    //xml에서 다이렉트로 들어옴
    fun clickLoginBtn(userId: String, userPasswd: String) {
        val memberDTO = MemberDTO(ur_id = userId, ur_passwd = userPasswd)
        callLoginApi(memberDTO)
    }

    private fun callLoginApi(memberDTO: MemberDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = runCatching {
                val response = memberApiService.loginApi(memberDTO)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val loginResult = responseBody["result"] as String
                        if (loginResult == "success") {
                            println("로그인 성공!!!")
                            Log.d("result","로그인 성공!!!")
                            _loginResult.postValue(true)
                            // 로그인 성공 시 처리할 로직
                        } else {
                            println("로그인 실패: ${responseBody["message"]}")
                            Log.d("result","로그인 실패!!!")
                            _loginResult.postValue(false)
                            // 로그인 실패 시 처리할 로직
                        }
                    } else {
                        println("API 응답이 올바르지 않습니다.")
                        Log.d("result","API 응답이 올바르지 않습니다.!!!")
                        // API 응답이 올바르지 않은 경우 처리할 로직
                        _loginResult.postValue(false)
                    }
                } else {
                    println("API 호출 실패: ${response.message()}")
                    Log.d("result","API 호출 실패!!!")
                    _loginResult.postValue(false)
                    // API 호출 실패 시 처리할 로직
                }
            }
            result.onFailure { e ->
                println("네트워크 오류 발생: ${e.message}")
                // 네트워크 오류 등 예외 발생 시 처리할 로직
                _loginResult.postValue(false)
            }
        }
    }

}