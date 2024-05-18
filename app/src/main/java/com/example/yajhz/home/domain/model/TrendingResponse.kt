package com.example.yajhz.home.domain.model

data class TrendingResponse(
    val `data`: List<Trending>,
    val message: String,
    val response_code: Int,
    val success: Boolean
)