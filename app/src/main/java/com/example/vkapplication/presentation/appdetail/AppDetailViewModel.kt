package com.example.vkapplication.presentation.appdetail

import androidx.lifecycle.ViewModel
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.usecase.GetAppByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppDetailUiState(
    val app: App? = null
)

class AppDetailViewModel(
    private val getAppByIdUseCase: GetAppByIdUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppDetailUiState())
    val uiState: StateFlow<AppDetailUiState> = _uiState.asStateFlow()

    private var loadedAppId: Int? = null

    fun loadApp(appId: Int) {
        if (loadedAppId == appId && _uiState.value.app != null) return
        loadedAppId = appId
        _uiState.value = AppDetailUiState(app = getAppByIdUseCase(appId))
    }
}
