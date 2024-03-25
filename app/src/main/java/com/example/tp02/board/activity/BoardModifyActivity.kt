package com.example.tp02.board.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.board.adapter.BoardCategoryAdapter
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.databinding.ActivityBoardmodifyBinding
import com.example.tp02.interceptor.TokenInterceptor
import com.example.tp02.member.activity.LoginActivity
import com.example.tp02.member.repository.MemberDataRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardModifyActivity :AppCompatActivity() {
    private lateinit var binding: ActivityBoardmodifyBinding
    private lateinit var boardViewModel: BoardViewModel
    private lateinit var boardCategoryAdapter: BoardCategoryAdapter
    private var bd_no: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boardmodify)
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

        bd_no = intent.getIntExtra("bd_no",0)

        boardViewModel.boardDetailData.observe(this, Observer { boardDetailList ->
            if(boardDetailList.isNotEmpty()) {
                val boardDetail = boardDetailList[0]
                binding.bdName.hint = "${boardDetail.bd_name}"
                binding.bdInfo.hint = "${boardDetail.bd_info}"

                val prevSelectedValue = boardDetail.bd_type
                val adapter = binding.bdTypeSpinner.adapter as ArrayAdapter<String>
                val engToKorMap = mapOf(
                    "general" to "일반",
                    "special" to "특별",
                    "anonymous" to "익명",
                    "nothing" to "기타"
                )
                val prevSelKorVal = engToKorMap[prevSelectedValue]
                val prevSelPosition = adapter.getPosition(prevSelKorVal)
                if(prevSelPosition != -1) {
                    binding.bdTypeSpinner.setSelection(prevSelPosition)
                }
            }
        })
        boardViewModel.getBoard(bd_no)

        boardCategoryAdapter = BoardCategoryAdapter()

        val spinnerData = boardCategoryAdapter.getCategoryData()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bdTypeSpinner.adapter = adapter

        binding.buttonModify.setOnClickListener {
            var bd_name = binding.bdName.text.toString()
            var bd_info = binding.bdInfo.text.toString()
            var bd_type = binding.bdTypeSpinner.getSelectedItem().toString()
            boardViewModel.clickModifyBtn(bd_no, bd_name, bd_info, bd_type)
        }

        boardViewModel.modifyResult.observe(this, Observer { result ->
            if(result) {
                showToast("수정 성공")
                goBoardDetailActivity(bd_no)
            } else {
                showToast("수정 실패")
            }
        })
    }
    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
    private fun goBoardDetailActivity(bd_no:Int) {
        val intent = Intent(this, BoardDetailActivity::class.java)
        intent.putExtra("bd_no",bd_no)
        startActivity(intent)
    }

    private fun goLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}