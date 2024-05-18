package com.example.yajhz.login.domain.model

data class LoginRequest(
    val email:String,
    val password:String,
    val device_token:String
)
