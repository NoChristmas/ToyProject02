package com.example.tp02.member.dto

import java.sql.Date

data class MemberDTO(
    val ur_no: Int? = null,
    val ur_id: String? = null,
    val ur_passwd: String? = null,
    val ur_name: String? = null,
    val ur_email: String? = null,
    val ur_birth_date: Date? = null,
    val ur_reg_date: Date? = null
)
