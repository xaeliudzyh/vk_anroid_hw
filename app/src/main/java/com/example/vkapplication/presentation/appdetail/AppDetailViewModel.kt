package com.example.vkapplication.presentation.appdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.usecase.GetAppDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AppDetailUiState(
    val app: App? = null
)

class AppDetailViewModel(
    private val appId: String,
    private val getAppDetailsUseCase: GetAppDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppDetailUiState())
    val uiState: StateFlow<AppDetailUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadApp()
        }
    }

    private suspend fun loadApp() {
        _uiState.value = AppDetailUiState(app = getAppDetailsUseCase(appId))
    }
}
