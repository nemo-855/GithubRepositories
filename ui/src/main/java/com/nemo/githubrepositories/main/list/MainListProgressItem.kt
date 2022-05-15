package com.nemo.githubrepositories.main.list

import android.view.View
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.databinding.CircularProgressLayoutBinding
import com.xwray.groupie.viewbinding.BindableItem

class MainListProgressItem : BindableItem<CircularProgressLayoutBinding>() {
    override fun bind(viewBinding: CircularProgressLayoutBinding, position: Int) {
    }

    override fun getLayout() = R.layout.circular_progress_layout

    override fun initializeViewBinding(view: View) = CircularProgressLayoutBinding.bind(view)
}
