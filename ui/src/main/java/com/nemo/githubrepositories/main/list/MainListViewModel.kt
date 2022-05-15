package com.nemo.githubrepositories.main.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemo.data.models.GithubProject
import com.nemo.data.repositories.interfaces.GithubRepository
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.ProgressIndicatorUiModel
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.ProjectUiModel
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.TextAndImageUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _uiModelListLD = MutableLiveData<List<MainListUiModel>>(
        listOf(
            TextAndImageUiModel(
                textResId = R.string.input_username,
                imageResId = R.drawable.magnifying_glass
            )
        )
    )
    val uiModelListLD: LiveData<List<MainListUiModel>>
        get() = _uiModelListLD

    fun onClickSearchButton(userName: String) {
        viewModelScope.launch {
            _uiModelListLD.value = listOf(ProgressIndicatorUiModel)
            runCatching {
                val fetchedProjectList = githubRepository.fetchGithubProjects(userName)
                _uiModelListLD.value = when (fetchedProjectList.isEmpty()) {
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
                _uiModelListLD.value = listOf(
                    TextAndImageUiModel(
                        textResId = R.string.failed_connection,
                        imageResId = R.drawable.loudly_crying_face
                    )
                )
            }
        }
    }

    private fun GithubProject.toProjectUiModel() = ProjectUiModel(
        id = id,
        name = name,
        isPrivate = isPrivate,
        ownerName = ownerName,
        createdTime = createdTime
    )

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
            val createdTime: ZonedDateTime
        ) : MainListUiModel()
    }
}
