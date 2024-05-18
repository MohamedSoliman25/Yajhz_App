package com.example.yajhz.home.domain.model

data class PopularResponse(
    val `data`: List<Popular>,
    val message: String,
    val response_code: Int,
    val success: Boolean
)