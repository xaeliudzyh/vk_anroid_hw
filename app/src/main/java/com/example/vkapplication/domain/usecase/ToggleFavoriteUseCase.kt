package com.example.vkapplication.domain.usecase

import com.example.vkapplication.domain.repository.AppDetailsRepository

class ToggleFavoriteUseCase(
    private val repository: AppDetailsRepository
) {
    suspend operator fun invoke(id: String) {
        repository.toggleWishlist(id)
    }
}

