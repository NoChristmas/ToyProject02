package com.example.tp02.member.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp02.member.api.MemberApiService
import com.example.tp02.member.dto.MemberDTO
import com.example.tp02.member.repository.MemberDataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

//Service같은 놈
class MemberViewModel(private val memberApiService: MemberApiService, private val repository: MemberDataRepository) : ViewModel() {
    //loginResult
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult:LiveData<Boolean>get() = _loginResult
    //ID 중복 체크
    private val _checkIdResult = MutableLiveData<Boolean>()
    val checkIdResult:LiveData<Boolean>get() = _checkIdResult
    //회원가입
    private val _registerResult = MutableLiveData<Boolean>()
    val registerResult:LiveData<Boolean>get() = _registerResult

    //xml에서 다이렉트로 들어옴
    fun clickLoginBtn(userId: String, userPasswd: String) {
        val memberDTO = MemberDTO(ur_id = userId, ur_passwd = userPasswd)
        callLoginApi(memberDTO)
    }

    fun clickIdCheckBtn(userId: String) {
        val ur_id = userId
        callCheckIdDuplicationApi(ur_id)
    }

    fun clickRegisterBtn(userId: String, userPasswd: String, userEmail:String, userName:String) {
        val memberDTO = MemberDTO(ur_id = userId, ur_passwd = userPasswd, ur_email = userEmail, ur_name = userName)
        callRegisterMemberApi(memberDTO)
    }

    //로그인 성공여부 API
    private fun callLoginApi(memberDTO: MemberDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = runCatching {
                val response = memberApiService.loginApi(memberDTO)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val loginResult = responseBody["result"] as String
                        if (loginResult == "success") {
                            // 로그인 성공 시 처리할 로직
                            Log.d("result","로그인 성공!!!")
                            _loginResult.postValue(true)
                            val token = responseBody["token"].toString()
                            repository.saveToken(token)
                        } else {
                            // 로그인 실패 시 처리할 로직
                            Log.d("result","로그인 실패 ${responseBody["message"]}")
                            _loginResult.postValue(false)
                        }
                    } else {
                        Log.d("result","API 응답이 올바르지 않습니다.!!!")
                        // API 응답이 올바르지 않은 경우 처리할 로직
                        _loginResult.postValue(false)
                    }
                } else {
                    Log.d("result","API 호출 실패 ${response.message()}")
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

    //아이디 중복체크 API
    private fun callCheckIdDuplicationApi(ur_id : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = runCatching {
                val response = memberApiService.checkIdDuplicationApi(ur_id)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val loginResult = responseBody["result"] as String
                        if (loginResult == "success") {
                            Log.d("result","사용가능한 아이디입니다.")
                            _checkIdResult.postValue(true)
                            // 로그인 성공 시 처리할 로직
                        } else {
                            Log.d("result","${response.message()}")
                            _checkIdResult.postValue(false)
                            // 로그인 실패 시 처리할 로직
                        }
                    } else {
                        Log.d("result","API 응답이 올바르지 않습니다.!!!")
                        // API 응답이 올바르지 않은 경우 처리할 로직
                        _checkIdResult.postValue(false)
                    }
                } else {
                    Log.d("result","API 호출 실패!!! ${response.message()}")
                    _checkIdResult.postValue(false)
                    // API 호출 실패 시 처리할 로직
                }
            }
            result.onFailure { e ->
                Log.d("네트워크 오류 발생:", "${e.message}")
                // 네트워크 오류 등 예외 발생 시 처리할 로직
                _checkIdResult.postValue(false)
            }
        }
    }

    private fun callRegisterMemberApi(memberDTO : MemberDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = runCatching {
                val response = memberApiService.registerMember(memberDTO)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val loginResult = responseBody["result"] as String
                        if (loginResult == "success") {
                            Log.d("result","회원가입 성공!!!")
                            _registerResult.postValue(true)
                            // 로그인 성공 시 처리할 로직
                        } else {
                            Log.d("result","${response.message()}")
                            _registerResult.postValue(false)
                            // 로그인 실패 시 처리할 로직
                        }
                    } else {
                        Log.d("result","API 응답이 올바르지 않습니다.!!!")
                        // API 응답이 올바르지 않은 경우 처리할 로직
                        _registerResult.postValue(false)
                    }
                } else {
                    Log.d("result","API 호출 실패!!!")
                    _registerResult.postValue(false)
                    // API 호출 실패 시 처리할 로직
                }
            }
            result.onFailure { e ->
                Log.d("네트워크 오류 발생:", "${e.message}")
                // 네트워크 오류 등 예외 발생 시 처리할 로직
                _registerResult.postValue(false)
            }
        }
    }
}