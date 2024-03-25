package com.example.tp02.board.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.tp02.R
import com.example.tp02.board.adapter.BoardAdapter
import com.example.tp02.board.api.BoardApiService
import com.example.tp02.board.viewmodel.BoardViewModel
import com.example.tp02.databinding.ActivityBoarddetailBinding
import com.example.tp02.interceptor.TokenInterceptor
import com.example.tp02.member.activity.LoginActivity
import com.example.tp02.member.repository.MemberDataRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BoardDetailActivity : BaseBoardActivity() {
    private lateinit var binding : ActivityBoarddetailBinding
    private lateinit var boardAdapter: BoardAdapter
    private var bd_no: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_boarddetail)

        bd_no = intent.getIntExtra("bd_no",0)
        boardViewModel.boardDetailData.observe(this, Observer { boardDetailList ->
            if(boardDetailList.isNotEmpty()) {
                val boardDetail = boardDetailList[0]
                binding.bdNo.text = "번호: ${boardDetail.bd_no}"
                binding.bdName.text = "제목: ${boardDetail.bd_name}"
                binding.bdInfo.text = "내용: ${boardDetail.bd_info}"
                binding.urName.text = "작성자: ${boardDetail.ur_name}"
                binding.bdHit.text = "조회수: ${boardDetail.bd_hit}"
                binding.bdRegDate.text = "작성날짜: ${boardDetail.bd_reg_date}"
            }
        })

        boardViewModel.deleteResult.observe(this, Observer { result ->
            if(result) {
                showToast("삭제 성공")
                goBoardMainActivity()
            } else {
                showToast("삭제 실패")
            }
        })

        boardViewModel.getBoard(bd_no)

        binding.buttonLogout.setOnClickListener {
            memberDataRepository.clearToken()
            goLoginActivity()
        }

        binding.buttonModify.setOnClickListener {
            goBoardModifyActivity(bd_no)
        }

        binding.buttonDelete.setOnClickListener {
            boardViewModel.deleteBoard(bd_no)
        }
    }

    private fun goBoardModifyActivity(bd_no:Int) {
        val intent = Intent(this, BoardModifyActivity::class.java)
        intent.putExtra("bd_no",bd_no)
        startActivity(intent)
    }

    private fun goLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun goBoardMainActivity() {
        startActivity(Intent(this, BoardMainActivity::class.java))
    }

    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}