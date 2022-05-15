package com.nemo.githubrepositories.main.list

import android.view.View
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.databinding.MainListProjectItemBinding
import com.nemo.githubrepositories.util.formatTime
import com.xwray.groupie.viewbinding.BindableItem

class MainListProjectItem(
    private val uiModel: MainListViewModel.MainListUiModel.ProjectUiModel
) : BindableItem<MainListProjectItemBinding>(
    uiModel.id.toLong()
) {
    override fun bind(binding: MainListProjectItemBinding, position: Int) {
        val context = binding.root.context
        binding.userName.text = uiModel.ownerName
        binding.projectName.text = uiModel.name
        binding.lastUpdatedText.text = context.getString(
            R.string.last_update,
            uiModel.createdTime.formatTime(context.getString(R.string.date_format))
        )
    }

    override fun getLayout() = R.layout.main_list_project_item

    override fun initializeViewBinding(view: View) = MainListProjectItemBinding.bind(view)
}
