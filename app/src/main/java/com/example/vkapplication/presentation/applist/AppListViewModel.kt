package com.example.vkapplication.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vkapplication.di.AppContainer
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.usecase.GetAppsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppListUiState(
    val apps: List<App> = emptyList()
)

class AppListViewModel(
    private val getAppsUseCase: GetAppsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = AppListUiState(apps = getAppsUseCase())
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AppListViewModel(AppContainer.getAppsUseCase) as T
            }
        }
    }
}

