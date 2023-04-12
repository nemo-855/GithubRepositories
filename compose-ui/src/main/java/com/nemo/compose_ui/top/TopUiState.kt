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
                projects = emptyList(),
                hasErrorOccurred = false,
                isLoading = false,
            ),
        )
    }

    fun onErrorOccurred() = this.copy(
        content = ContentUiModel(
            hasNotSearched = false,
            projects = emptyList(),
            hasErrorOccurred = true,
            isLoading = false,
        )
    )

    fun onDataFetched(newProjects: List<GithubProject>) = this.copy(
        content = this.content.copy(
            hasNotSearched = false,
            hasErrorOccurred = false,
            projects = newProjects,
            isLoading = false,
        )
    )

    fun onStartLoading() = this.copy(
        content = this.content.copy(
            isLoading = true
        )
    )
}

data class SearchBarUiModel(
    val text: String
)

data class ContentUiModel(
    val hasNotSearched: Boolean,
    val projects: List<GithubProject>,
    val hasErrorOccurred: Boolean,
    val isLoading: Boolean
)