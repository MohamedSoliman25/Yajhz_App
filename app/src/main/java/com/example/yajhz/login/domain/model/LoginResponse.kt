package com.example.yajhz.login.domain.model

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val response_code: Int,
    val success: Boolean
)