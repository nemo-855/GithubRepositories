package com.nemo.githubrepositories.main.list

import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.ProgressIndicatorUiModel
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.ProjectUiModel
import com.nemo.githubrepositories.main.list.MainListViewModel.MainListUiModel.TextAndImageUiModel
import com.xwray.groupie.GroupieAdapter

class MainListAdapter : GroupieAdapter() {
    fun submitList(newList: List<MainListViewModel.MainListUiModel>) {
        updateAsync(
            newList.map { uiModel ->
                when (uiModel) {
                    is ProgressIndicatorUiModel -> MainListProgressItem()
                    is TextAndImageUiModel -> MainListTextItem(uiModel)
                    is ProjectUiModel -> MainListProjectItem(uiModel)
                }
            }
        )
    }
}
