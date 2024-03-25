package com.example.tp02.board.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.board.adapter.BoardCategoryAdapter
import com.example.tp02.databinding.ActivityBoardwriteBinding

class BoardWriteActivity: BaseBoardActivity() {
    private lateinit var binding: ActivityBoardwriteBinding
    private lateinit var boardCategoryAdapter: BoardCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boardwrite)

        binding.boardViewModel = boardViewModel
        boardCategoryAdapter = BoardCategoryAdapter()

        //Spinner 설정
        val spinnerData = boardCategoryAdapter.getCategoryData()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bdTypeSpinner.adapter = adapter

        boardViewModel.writeResult.observe(this, Observer {success->
            if (success) { //로그인 성공시 로직
                showToast("작성 성공")
                goBoardMainActivity()
            } else {
                showToast("작성 실패")
            }
        })
    }
    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun goBoardMainActivity() {
        startActivity(Intent(this,BoardMainActivity::class.java))
    }
}