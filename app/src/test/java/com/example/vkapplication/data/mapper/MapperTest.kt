package com.example.vkapplication.data.mapper

import com.example.vkapplication.data.appdetails.local.AppDetailsEntity
import com.example.vkapplication.data.appdetails.local.AppDetailsEntityMapper
import com.example.vkapplication.data.appdetails.mapper.AppDetailsMapper
import com.example.vkapplication.data.dto.AppDto
import com.example.vkapplication.data.dto.NetworkAppDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MapperTest {

    @Test
    fun networkDto_toData_mapsAllFields() {
        val dto = NetworkAppDto(
            id = "id-1",
            name = "App",
            developer = "Dev",
            category = "Games",
            rating = 4.9f,
            reviewCount = "500",
            downloadsCount = "10K",
            size = "120 MB",
            description = "Great app",
            iconUrl = "https://icon",
            isFree = false
        )

        val result = dto.toData()

        assertEquals("id-1", result.id)
        assertEquals("App", result.name)
        assertEquals("Dev", result.developer)
        assertEquals("Games", result.category)
        assertEquals(4.9f, result.rating)
        assertEquals("500", result.reviewCount)
        assertEquals("10K", result.downloadsCount)
        assertEquals("120 MB", result.size)
        assertEquals("Great app", result.description)
        assertEquals("https://icon", result.iconUrl)
        assertEquals(false, result.isFree)
    }

    @Test
    fun networkDto_toData_appliesDefaults_whenNulls() {
        val dto = NetworkAppDto(
            id = null,
            name = null,
            developer = null,
            category = null,
            rating = null,
            reviewCount = null,
            downloadsCount = null,
            size = null,
            description = null,
            iconUrl = null,
            isFree = null
        )

        val result = dto.toData()

        assertEquals("", result.id)
        assertEquals("", result.name)
        assertEquals("", result.developer)
        assertEquals("", result.category)
        assertEquals(0f, result.rating)
        assertEquals("", result.reviewCount)
        assertEquals("", result.downloadsCount)
        assertEquals("", result.size)
        assertEquals("", result.description)
        assertEquals("", result.iconUrl)
        assertTrue(result.isFree)
        assertEquals(false, result.isInWishlist)
    }

    @Test
    fun appDto_toDomain_mapsWishlistField() {
        val dto = AppDto(
            id = "id",
            name = "Name",
            developer = "Dev",
            category = "Category",
            rating = 1.5f,
            reviewCount = "1",
            downloadsCount = "2",
            size = "3",
            description = "4",
            iconUrl = "5",
            isFree = true,
            isInWishlist = true
        )

        val result = dto.toDomain()

        assertEquals("id", result.id)
        assertEquals(true, result.isInWishlist)
        assertEquals(true, result.isFree)
    }

    @Test
    fun appDetailsMapper_toData_mapsDtoToAppDto() {
        val mapper = AppDetailsMapper()
        val dto = NetworkAppDto(
            id = "id-2",
            name = "Detailed",
            developer = "Studio",
            category = "Tools",
            rating = 3.7f,
            reviewCount = "77",
            downloadsCount = "900",
            size = "19 MB",
            description = "Desc",
            iconUrl = "icon",
            isFree = null
        )

        val result = mapper.toData(dto)

        assertEquals("id-2", result.id)
        assertEquals("Detailed", result.name)
        assertEquals(true, result.isFree)
        assertEquals(false, result.isInWishlist)
    }

    @Test
    fun appDetailsEntityMapper_toEntityAndToDomain_preservesFields() {
        val mapper = AppDetailsEntityMapper()
        val dto = AppDto(
            id = "id-3",
            name = "Entity App",
            developer = "Dev",
            category = "Cat",
            rating = 4.0f,
            reviewCount = "10",
            downloadsCount = "11",
            size = "12",
            description = "13",
            iconUrl = "14",
            isFree = false,
            isInWishlist = true
        )

        val entity = mapper.toEntity(dto)
        val domain = mapper.toDomain(
            AppDetailsEntity(
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
                isFree = entity.isFree,
                isInWishlist = entity.isInWishlist,
                lastUpdated = 1L
            )
        )

        assertEquals("id-3", entity.id)
        assertEquals(true, entity.isInWishlist)
        assertEquals(false, domain.isFree)
        assertEquals(true, domain.isInWishlist)
        assertEquals("Entity App", domain.name)
    }
}
