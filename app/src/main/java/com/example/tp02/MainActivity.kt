package com.example.tp02

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tp02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //선언 부분
    lateinit var btnAdd : Button
    lateinit var btnSub : Button
    lateinit var btnMultiply : Button
    lateinit var btnDivision : Button
    lateinit var etA : EditText
    lateinit var etB : EditText
    lateinit var resultA : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btn_add)
        btnSub = findViewById(R.id.btn_sub)
        btnMultiply = findViewById(R.id.btn_multiple)
        btnDivision = findViewById(R.id.btn_division)
        etA = findViewById(R.id.et_a)
        etB = findViewById(R.id.et_b)
        resultA = findViewById(R.id.resultA)


        btnAdd.setOnClickListener{
            val a = etA.text.toString().toIntOrNull()
            val b = etB.text.toString().toIntOrNull()

            if(a != null && b != null) {
                val result = a+b
                resultA.text = "Result : $result" //결과를 텍스트 뷰에 표시

            } else {
                resultA.text = "Please enter valid numers"
            }
        }


    }

}