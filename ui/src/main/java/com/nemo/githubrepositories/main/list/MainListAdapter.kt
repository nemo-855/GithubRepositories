package com.nemo.githubrepositories.main.list

import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.EmptyUiModel
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.ProgressIndicatorUiModel
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.ProjectUiModel
import com.xwray.groupie.GroupieAdapter

class MainListAdapter : GroupieAdapter() {
    fun submitList(newList: List<MainListViewModel.MainListUiModel>) {
        updateAsync(
            newList.map { uiModel ->
                when (uiModel) {
                    is ProgressIndicatorUiModel -> MainListProgressItem()
                    // TODO: EmptyUiModelに対応するItemを作成する
                    is EmptyUiModel -> MainListProgressItem()
                    is ProjectUiModel -> MainListProjectItem(uiModel)
                }
            }
        )
    }
}
