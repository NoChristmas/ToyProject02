package com.example.tp02.board.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.dto.BoardDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BoardViewModel (private val boardApiService : BoardApiService) {

    private val _boardData = MutableLiveData<List<BoardDTO>>()
    val boardData: LiveData<List<BoardDTO>>
        get() = _boardData

    private val _boardDataCount = MutableLiveData<Int>()
    val boardDataCount: LiveData<Int>
        get() = _boardDataCount

    fun getBoards() {
        callGetBoardsApi()
    }

    private fun callGetBoardsApi() {
        CoroutineScope(Dispatchers.IO).launch { //비동기 처리
            val result = runCatching {
                val response = boardApiService.getBoardsApi()
                if(response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) { //성공시
                        val getBoardsResult = responseBody["result"] as String
                        if (getBoardsResult == "success") {
                            Log.d("result", "게시판 가져오기 성공")
                            _boardData.postValue(responseBody["list"] as List<BoardDTO>)
                            _boardDataCount.postValue(responseBody["count"] as? Int)
                        } else {
                            Log.d("result", "response가 없음")
                        }
                    } else {
                        Log.d("result","API 응답이 올바르지 않습니다.")
                    }
                } else { //api 요청 실패 시
                    Log.d("result","API 호출 실패")
                }
            }
            result.onFailure { e ->
                println("네트워크 오류 발생: ${e.message}")
                // 네트워크 오류 등 예외 발생 시 처리할 로직
            }
        }
    }
}