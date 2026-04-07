package com.example.vkapplication.domain.usecase

import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.repository.AppDetailsRepository
import kotlinx.coroutines.flow.Flow

class ObserveAppDetailsUseCase(
    private val repository: AppDetailsRepository
) {
    operator fun invoke(id: String): Flow<App> = repository.observeAppDetails(id)
}

