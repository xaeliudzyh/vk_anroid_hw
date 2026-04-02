package com.example.vkapplication.presentation.appdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.usecase.GetAppDetailsUseCase
import com.example.vkapplication.domain.usecase.ObserveAppDetailsUseCase
import com.example.vkapplication.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

data class AppDetailUiState(
    val app: App? = null,
    val isInWishlist: Boolean = false,
    val isError: Boolean = false
)

class AppDetailViewModel(
    private val appId: String,
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val observeAppDetailsUseCase: ObserveAppDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppDetailUiState())
    val uiState: StateFlow<AppDetailUiState> = _uiState.asStateFlow()

    init {
        observeAppDetails()
        viewModelScope.launch {
            loadApp()
        }
    }

    private fun observeAppDetails() {
        viewModelScope.launch {
            observeAppDetailsUseCase(appId)
                .catch {
                    _uiState.value = _uiState.value.copy(isError = true)
                }
                .collect { app ->
                    _uiState.value = AppDetailUiState(
                        app = app,
                        isInWishlist = app.isInWishlist,
                        isError = false
                    )
                }
        }
    }

    private suspend fun loadApp() {
        if (getAppDetailsUseCase(appId) == null) {
            _uiState.value = _uiState.value.copy(isError = true)
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            toggleFavoriteUseCase(appId)
        }
    }
}
