package com.example.vkapplication.data.appdetails.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_details")
data class AppDetailsEntity(
    @PrimaryKey
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
    val isFree: Boolean,
    val lastUpdated: Long = System.currentTimeMillis()
)

