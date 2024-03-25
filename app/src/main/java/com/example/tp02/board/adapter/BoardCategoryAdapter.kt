package com.example.tp02.board.adapter

class BoardCategoryAdapter {
    fun getCategoryData(): List<String> {
        return listOf("일반","특별","익명")
    }
    fun getCategoryValues(): List<String> {
        return listOf("general", "special", "anonymous")
    }
}