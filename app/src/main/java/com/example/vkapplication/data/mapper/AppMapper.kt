package com.example.vkapplication.data.mapper

import com.example.vkapplication.data.dto.AppDto
import com.example.vkapplication.data.dto.NetworkAppDto
import com.example.vkapplication.domain.model.App

fun NetworkAppDto.toData(): AppDto = AppDto(
    id = id.orEmpty(),
    name = name.orEmpty(),
    developer = developer.orEmpty(),
    category = category.orEmpty(),
    rating = rating ?: 0f,
    reviewCount = reviewCount.orEmpty(),
    downloadsCount = downloadsCount.orEmpty(),
    size = size.orEmpty(),
    description = description.orEmpty(),
    iconUrl = iconUrl.orEmpty(),
    isFree = isFree ?: true,
    isInWishlist = false
)

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
    isFree = isFree,
    isInWishlist = isInWishlist
)
