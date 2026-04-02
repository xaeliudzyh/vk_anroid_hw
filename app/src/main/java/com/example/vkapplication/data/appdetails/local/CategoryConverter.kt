package com.example.vkapplication.data.appdetails.local

import androidx.room.TypeConverter

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: String): String = category

    @TypeConverter
    fun toCategory(categoryName: String): String = categoryName
}

