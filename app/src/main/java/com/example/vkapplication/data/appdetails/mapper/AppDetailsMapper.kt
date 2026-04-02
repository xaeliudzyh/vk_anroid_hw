package com.example.vkapplication.data.appdetails.mapper

import com.example.vkapplication.data.dto.AppDto
import com.example.vkapplication.data.dto.NetworkAppDto

class AppDetailsMapper {
    fun toData(dto: NetworkAppDto): AppDto = AppDto(
        id = dto.id.orEmpty(),
        name = dto.name.orEmpty(),
        developer = dto.developer.orEmpty(),
        category = dto.category.orEmpty(),
        rating = dto.rating ?: 0f,
        reviewCount = dto.reviewCount.orEmpty(),
        downloadsCount = dto.downloadsCount.orEmpty(),
        size = dto.size.orEmpty(),
        description = dto.description.orEmpty(),
        iconUrl = dto.iconUrl.orEmpty(),
        isFree = dto.isFree ?: true
    )
}

