package com.example.vkapplication.data

data class AppItem(
    val id: Int,
    val name: String,
    val developer: String,
    val category: String,
    val rating: Float,
    val reviewCount: String,
    val downloadsCount: String,
    val size: String,
    val description: String,
    val iconUrl: String,
    val isFree: Boolean = true
)
