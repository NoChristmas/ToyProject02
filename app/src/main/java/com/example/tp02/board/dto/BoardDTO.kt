package com.example.tp02.board.dto

import java.util.Date

data class BoardDTO (
    val bd_no: Int? = 0,
    val ur_no: Int? = 0,
    val ur_name: String? = null,
    val bd_name: String? = null,
    val bd_info: String? = null,
    val bd_type: String? = null,
    val bd_auth: Int? = 4,
    val bd_hit: Int? = 0,
    val bd_reg_date: String? = null // Date 대신 String으로 변경
)
