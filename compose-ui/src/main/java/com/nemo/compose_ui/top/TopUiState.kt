package com.nemo.compose_ui.top

import androidx.compose.runtime.Stable
import com.nemo.githubrepositories_kmm.data.models.GithubProject

@Stable
class TopUiState(
    val isInitial: Boolean,
    val repository: GithubProject?,
    val hasErrorOccurred: Boolean,
) {
    companion object {
        fun initial() = TopUiState(
            isInitial = true,
            repository = null,
            hasErrorOccurred = false,
        )
    }
}
