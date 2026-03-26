package com.example.vkapplication.presentation.appdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.usecase.GetAppByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AppDetailUiState(
    val app: App? = null
)

class AppDetailViewModel(
    private val getAppByIdUseCase: GetAppByIdUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppDetailUiState())
    val uiState: StateFlow<AppDetailUiState> = _uiState.asStateFlow()

    private var loadedAppId: String? = null

    fun loadApp(appId: String) {
        if (loadedAppId == appId && _uiState.value.app != null) return
        loadedAppId = appId
        viewModelScope.launch {
            _uiState.value = AppDetailUiState(app = getAppByIdUseCase(appId))
        }
    }
}
