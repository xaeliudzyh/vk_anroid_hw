package com.example.vkapplication.domain.usecase

import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppRepository

class GetAppByIdUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(appId: String): App? = repository.getAppById(appId)
}
