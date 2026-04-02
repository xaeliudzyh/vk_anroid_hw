package com.example.vkapplication.data.appdetails.local

import com.example.vkapplication.data.dto.AppDto
import com.example.vkapplication.domain.model.App

class AppDetailsEntityMapper {
    fun toEntity(data: AppDto): AppDetailsEntity = AppDetailsEntity(
        id = data.id,
        name = data.name,
        developer = data.developer,
        category = data.category,
        rating = data.rating,
        reviewCount = data.reviewCount,
        downloadsCount = data.downloadsCount,
        size = data.size,
        description = data.description,
        iconUrl = data.iconUrl,
        isFree = data.isFree
    )

    fun toDomain(entity: AppDetailsEntity): App = App(
        id = entity.id,
        name = entity.name,
        developer = entity.developer,
        category = entity.category,
        rating = entity.rating,
        reviewCount = entity.reviewCount,
        downloadsCount = entity.downloadsCount,
        size = entity.size,
        description = entity.description,
        iconUrl = entity.iconUrl,
        isFree = entity.isFree
    )
}

