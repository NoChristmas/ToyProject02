package com.example.tp02.board.dto

import java.sql.Date
data class BoardDTO (
    val bd_no: Int,
    val ur_no: Int,
    val ur_name: String,
    val bd_name: String,
    val bd_info: String,
    val bd_type: String,
    val bd_auth: Int,
    val bd_hit: Int,
    val bd_reg_date: Date
)
