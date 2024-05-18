package com.example.yajhz.home.domain.model

data class Trending(
    val address: String,
    val appointments: String,
    val categories: List<CategoryX>,
    val description: String,
    val distance: String,
    val email: String,
    val id: Int,
    val image: String,
    val information: InformationX,
    val is_favorite: Boolean,
    val lat: String,
    val lng: String,
    val logo: String,
    val name: String,
    val phone: String,
    val popular: Int,
    val product_categories: List<ProductCategoryX>,
    val rate: String,
    val status: Int,
    val token: String,
    val trending: Int,
    val type: Int
)