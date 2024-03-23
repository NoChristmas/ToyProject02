package com.example.tp02.board.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import com.example.tp02.R
import com.example.tp02.board.adapter.BoardCategoryAdapter
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.databinding.ActivityBoardwriteBinding

class BoardWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoardwriteBinding
    private lateinit var boardViewModel: BoardViewModel
    private lateinit var boardCategoryAdapter: BoardCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boardwrite)
        boardCategoryAdapter = BoardCategoryAdapter()

        //Spinner 설정
        val spinnerData = boardCategoryAdapter.getCategoryData()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerData)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.bdTypeSpinner.adapter = adapter


    }


}