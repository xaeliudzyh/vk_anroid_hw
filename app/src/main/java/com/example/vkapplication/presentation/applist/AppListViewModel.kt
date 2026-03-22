package com.example.vkapplication.presentation.applist

import androidx.lifecycle.ViewModel
import com.example.vkapplication.domain.model.App
import com.example.vkapplication.domain.usecase.GetAppsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

data class AppListUiState(
    val apps: List<App> = emptyList()
)

sealed interface AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent
}

class AppListViewModel(
    private val getAppsUseCase: GetAppsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState.asStateFlow()

    private val eventChannel = Channel<AppListEvent>(Channel.BUFFERED)
    val events = eventChannel.receiveAsFlow()

    init {
        _uiState.value = AppListUiState(apps = getAppsUseCase())
    }

    fun onLogoClick() {
        eventChannel.trySend(AppListEvent.ShowSnackbar(message = "Логотип RuStore нажат"))
    }
}
