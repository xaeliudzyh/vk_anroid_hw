package com.example.vkapplication.domain.usecase

import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppRepository

class GetAppsUseCase(
    private val repository: AppRepository
) {
    operator fun invoke(): List<App> = repository.getApps()
}

