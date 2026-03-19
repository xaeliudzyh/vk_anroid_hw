package com.example.vkapplication.domain.repository

import com.example.vkapplication.domain.model.App

interface AppRepository {
    fun getApps(): List<App>
    fun getAppById(appId: Int): App?
}

