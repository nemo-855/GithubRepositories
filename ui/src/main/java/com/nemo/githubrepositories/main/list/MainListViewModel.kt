package com.nemo.githubrepositories.main.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemo.githubrepositories_kmm.data.models.GithubProject
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.ProgressIndicatorUiModel
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.ProjectUiModel
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.TextAndImageUiModel
import com.nemo.githubrepositories_kmm.domain.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val githubUseCase: GithubUseCase
) : ViewModel() {
    private val _uiModelListFlow = MutableStateFlow<List<MainListUiModel>>(
        listOf(
            TextAndImageUiModel(
                textResId = R.string.input_username,
                imageResId = R.drawable.magnifying_glass
            )
        )
    )
    val uiModelListFlow: StateFlow<List<MainListUiModel>>
        get() = _uiModelListFlow

    fun onClickSearchButton(userName: String) {
        viewModelScope.launch {
            runCatching {
                _uiModelListFlow.value = listOf(ProgressIndicatorUiModel)
                val fetchedProjectList = githubUseCase.fetchGithubProjects(userName)
                _uiModelListFlow.value = when (fetchedProjectList.isEmpty()) {
                    true -> listOf(
                        TextAndImageUiModel(
                            textResId = R.string.search_result_is_empty,
                            imageResId = R.drawable.crying_face
                        )
                    )
                    else -> fetchedProjectList.map {
                        it.toProjectUiModel()
                    }
                }
            }.onFailure {
                _uiModelListFlow.value = listOf(
                    TextAndImageUiModel(
                        textResId = R.string.failed_connection,
                        imageResId = R.drawable.loudly_crying_face
                    )
                )
            }
        }
    }

    sealed class MainListUiModel {
        object ProgressIndicatorUiModel : MainListUiModel()
        data class TextAndImageUiModel(
            @StringRes val textResId: Int,
            @DrawableRes val imageResId: Int?
        ) : MainListUiModel()
        data class ProjectUiModel(
            val id: Int,
            val name: String,
            val isPrivate: Boolean,
            val ownerName: String,
            val createdTime: LocalDateTime
        ) : MainListUiModel()
    }
}

@VisibleForTesting(otherwise = PRIVATE)
fun GithubProject.toProjectUiModel() = ProjectUiModel(
    id = id,
    name = name,
    isPrivate = isPrivate,
    ownerName = ownerName,
    createdTime = createdTime
)
