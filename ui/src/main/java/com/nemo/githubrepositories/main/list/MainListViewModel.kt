package com.nemo.githubrepositories.main.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemo.data.models.GithubProject
import com.nemo.data.repositories.interfaces.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _errorToastLD = MutableLiveData<Boolean>()
    val errorToastLD: LiveData<Boolean>
        get() = _errorToastLD

    private val _uiModelListLD = MutableLiveData<List<MainListUiModel>>(listOf(MainListUiModel.ProgressIndicatorUiModel))
    val uiModelListLD: LiveData<List<MainListUiModel>>
        get() = _uiModelListLD

    fun onClickSearchButton(userName: String) {
        viewModelScope.launch {
            runCatching {
                // FIXME: 入力できるようにする
                _uiModelListLD.value = githubRepository.fetchGithubProjects(userName).map {
                    it.toProjectUiModel()
                }
            }.onFailure {
                _errorToastLD.value = true
                _uiModelListLD.value = listOf(MainListUiModel.EmptyUiModel)
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
        object EmptyUiModel : MainListUiModel()
        data class ProjectUiModel(
            val id: Int,
            val name: String,
            val isPrivate: Boolean,
            val ownerName: String,
            val createdTime: ZonedDateTime
        ) : MainListUiModel()
    }
}
