package com.nemo.githubrepositories.main.list

import android.view.View
import androidx.core.content.ContextCompat
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.databinding.MainListTextItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class MainListTextItem(
    private val uiModel: MainListViewModel.MainListUiModel.TextAndImageUiModel
) : BindableItem<MainListTextItemBinding>() {
    override fun bind(binding: MainListTextItemBinding, position: Int) {
        val context = binding.root.context
        binding.mainText.text = context.getString(uiModel.textResId)
        if (uiModel.imageResId != null) {
            binding.mainImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    uiModel.imageResId
                )
            )
        }
    }

    override fun getLayout() = R.layout.main_list_text_item

    override fun initializeViewBinding(view: View) = MainListTextItemBinding.bind(view)
}
