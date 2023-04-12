package com.nemo.compose_ui.top

import androidx.compose.runtime.Stable
import com.nemo.githubrepositories_kmm.data.models.GithubProject

@Stable
data class TopUiState(
    val searchBar: SearchBarUiModel,
    val content: ContentUiModel,
) {
    companion object {
        fun initial() = TopUiState(
            searchBar = SearchBarUiModel(text = String()),
            content = ContentUiModel(
                hasNotSearched = true,
                repository = null,
                hasErrorOccurred = false
            ),
        )
    }
}

data class SearchBarUiModel(
    val text: String
)

data class ContentUiModel(
    val hasNotSearched: Boolean,
    val repository: GithubProject?,
    val hasErrorOccurred: Boolean,
)