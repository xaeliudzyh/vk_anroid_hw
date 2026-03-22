package com.example.vkapplication.data.repository

import com.example.vkapplication.data.datasource.StaticAppDataSource
import com.example.vkapplication.data.mapper.toDomain
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppRepository

class AppRepositoryImpl : AppRepository {
    override fun getApps(): List<App> = StaticAppDataSource.apps.map { it.toDomain() }

    override fun getAppById(appId: Int): App? = StaticAppDataSource.apps
        .firstOrNull { it.id == appId }
        ?.toDomain()
}
