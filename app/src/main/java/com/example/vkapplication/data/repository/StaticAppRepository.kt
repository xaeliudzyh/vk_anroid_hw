package com.example.vkapplication.data.repository

import com.example.vkapplication.data.mapper.toData
import com.example.vkapplication.data.mapper.toDomain
import com.example.vkapplication.data.network.CatalogApi
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppRepository

class AppRepositoryImpl(
    private val catalogApi: CatalogApi
) : AppRepository {
    override suspend fun getApps(): List<App> =
        catalogApi.getCatalog().map { it.toData().toDomain() }

    override suspend fun getAppById(appId: String): App? =
        runCatching { catalogApi.getAppById(appId).toData().toDomain() }.getOrNull()
}
