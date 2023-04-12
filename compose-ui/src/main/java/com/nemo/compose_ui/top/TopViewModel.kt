package com.nemo.compose_ui.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemo.githubrepositories_kmm.domain.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val githubUseCase: GithubUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(TopUiState.initial())
    val uiState: StateFlow<TopUiState> = _uiState.asStateFlow()

    fun onClickSearchButton(searchQuery: String) {
        viewModelScope.launch {
            val prevUiState = uiState.value

            runCatching {
                _uiState.value = prevUiState.onStartLoading()
                githubUseCase.fetchGithubProjects(userName = searchQuery)
            }.onSuccess { newProjects ->
                _uiState.value = prevUiState.onDataFetched(newProjects = newProjects)
            }.onFailure {
                _uiState.value = prevUiState.onErrorOccurred()
            }
        }
    }

    fun onSearchBarValueChanged(newValue: String) {
        _uiState.value = uiState.value.copy(
            searchBar = SearchBarUiModel(
                text = newValue
            )
        )
    }
}
