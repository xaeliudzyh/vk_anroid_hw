package com.example.vkapplication.domain.usecase

import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppDetailsRepository

class GetAppDetailsUseCase(
    private val repository: AppDetailsRepository
) {
    suspend operator fun invoke(id: String): App? = repository.getAppDetails(id)
}

