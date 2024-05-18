package com.example.yajhz.login.domain.model

data class Addresse(
    val address: String?,
    val apartment: String,
    val building: String,
    val floor: String?,
    val id: Int,
    val lat: String,
    val lng: String,
    val name: String?,
    val street: String
)