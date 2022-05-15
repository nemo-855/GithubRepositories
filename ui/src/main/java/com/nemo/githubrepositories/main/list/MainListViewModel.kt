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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _uiModelListLD = MutableLiveData<List<MainListUiModel>>(listOf(MainListUiModel.ProgressIndicatorUiModel))
    val uiModelListLD: LiveData<List<MainListUiModel>>
        get() = _uiModelListLD

    fun onClickSearchButton(userName: String) {
        viewModelScope.launch {
            runCatching {
                // FIXME: 入力できるようにする
                val fetchedProjectList = githubRepository.fetchGithubProjects(userName)
                _uiModelListLD.value = when (fetchedProjectList.isEmpty()) {
                    true -> listOf(
                        MainListUiModel.TextAndImageUiModel(
                            textResId = R.string.search_result_is_empty,
                            imageResId = R.drawable.crying_face
                        )
                    )
                    else -> fetchedProjectList.map {
                        it.toProjectUiModel()
                    }
                }
            }.onFailure {
                // FIXME: 通信エラー用に画像を修正
                _uiModelListLD.value = listOf(
                    MainListUiModel.TextAndImageUiModel(
                        textResId = R.string.failed_connection,
                        imageResId = R.drawable.crying_face
                    )
                )
            }
        }
    }

    private fun GithubProject.toProjectUiModel() = MainListUiModel.ProjectUiModel(
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
