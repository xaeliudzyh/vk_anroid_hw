package com.example.vkapplication.domain.repository

import com.example.vkapplication.domain.model.App

interface AppRepository {
    suspend fun getApps(): List<App>
    suspend fun getAppById(appId: String): App?
}
