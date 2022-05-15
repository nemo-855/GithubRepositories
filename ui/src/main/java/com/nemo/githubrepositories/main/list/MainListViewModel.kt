package com.nemo.githubrepositories.main.list

import androidx.lifecycle.ViewModel
import com.nemo.data.repositories.interfaces.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    sealed class MainListUiModel {
        object ProgressIndicatorUiModel : MainListUiModel()

        data class ProjectUiModel(
            val id: Int,
            val name: String,
            val isPrivate: Boolean,
            val ownerName: String,
            val createdUnixTime: ZonedDateTime
        ) : MainListUiModel()
    }
}
