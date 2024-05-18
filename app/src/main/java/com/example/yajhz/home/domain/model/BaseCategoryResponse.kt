package com.example.yajhz.home.domain.model

data class BaseCategoryResponse(
    val `data`: Data,
    val message: String,
    val response_code: Int,
    val success: Boolean
)