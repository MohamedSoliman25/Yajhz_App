package com.example.yajhz.signup.domain.model

data class SignUpResponse(
    val `data`: Data,
    val message: String,
    val response_code: Int,
    val success: Boolean
)