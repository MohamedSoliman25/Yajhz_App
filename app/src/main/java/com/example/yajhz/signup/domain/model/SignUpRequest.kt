package com.example.yajhz.signup.domain.model

data class SignUpRequest(
    val name:String,
    val email:String,
    val password:String,
    val confirm_password:String,
    val phone:String,
    val device_token:String
)
