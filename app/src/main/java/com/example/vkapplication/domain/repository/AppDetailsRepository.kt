package com.example.vkapplication.domain.repository

import com.example.vkapplication.domain.model.App
import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): App?
    fun observeAppDetails(id: String): Flow<App>
    suspend fun toggleWishlist(id: String)
}

