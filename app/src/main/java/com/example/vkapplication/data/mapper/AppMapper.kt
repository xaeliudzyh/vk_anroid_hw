package com.example.vkapplication.data.mapper

import com.example.vkapplication.data.dto.AppDto
import com.example.vkapplication.domain.model.App

fun AppDto.toDomain(): App = App(
    id = id,
    name = name,
    developer = developer,
    category = category,
    rating = rating,
    reviewCount = reviewCount,
    downloadsCount = downloadsCount,
    size = size,
    description = description,
    iconUrl = iconUrl,
    isFree = isFree
)

