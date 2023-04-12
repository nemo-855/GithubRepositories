package com.nemo.compose_ui.top

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(TopUiState.initial())
    val uiState: StateFlow<TopUiState> = _uiState.asStateFlow()

    fun onClickSearchButton() {
        // implement
    }

    fun onSearchBarValueChanged(newValue: String) {
        _uiState.value = uiState.value.copy(
            searchBar = SearchBarUiModel(
                text = newValue
            )
        )
    }
}
