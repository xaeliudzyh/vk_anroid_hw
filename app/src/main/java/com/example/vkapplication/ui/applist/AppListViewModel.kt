package com.example.vkapplication.ui.applist

import androidx.lifecycle.ViewModel
import com.example.vkapplication.data.AppItem
import com.example.vkapplication.data.AppRepository
import com.example.vkapplication.data.StaticAppRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

data class AppListUiState(
    val apps: List<AppItem> = emptyList()
)

sealed interface AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent
}

class AppListViewModel(
    repository: AppRepository = StaticAppRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppListUiState())
    val uiState: StateFlow<AppListUiState> = _uiState.asStateFlow()
    private val eventChannel = Channel<AppListEvent>(Channel.BUFFERED)
    val events = eventChannel.receiveAsFlow()
    init {
        _uiState.value = AppListUiState(apps = repository.getApps())
    }
    fun onLogoClick() {
        eventChannel.trySend(AppListEvent.ShowSnackbar(message = "Логотип RuStore нажат"))
    }
}
