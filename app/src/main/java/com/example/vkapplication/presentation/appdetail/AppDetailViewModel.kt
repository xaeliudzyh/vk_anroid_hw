package com.example.vkapplication.presentation.appdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vkapplication.di.AppContainer
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.usecase.GetAppByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppDetailUiState(
    val app: App? = null
)

class AppDetailViewModel(
    appId: Int,
    private val getAppByIdUseCase: GetAppByIdUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppDetailUiState())
    val uiState: StateFlow<AppDetailUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = AppDetailUiState(app = getAppByIdUseCase(appId))
    }

    class Factory(
        private val appId: Int
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AppDetailViewModel(appId, AppContainer.getAppByIdUseCase) as T
        }
    }
}

