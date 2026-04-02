package com.example.vkapplication.data.dto

data class AppDto(
    val id: String,
    val name: String,
    val developer: String,
    val category: String,
    val rating: Float,
    val reviewCount: String,
    val downloadsCount: String,
    val size: String,
    val description: String,
    val iconUrl: String,
    val isFree: Boolean = true,
    val isInWishlist: Boolean = false
)
