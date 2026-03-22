package com.example.vkapplication.domain.usecase

import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppRepository

class GetAppByIdUseCase(
    private val repository: AppRepository
) {
    operator fun invoke(appId: Int): App? = repository.getAppById(appId)
}
