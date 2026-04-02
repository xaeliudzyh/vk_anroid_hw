package com.example.vkapplication.domain.repository

import com.example.vkapplication.domain.model.App

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): App?
}

