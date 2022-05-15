package com.nemo.githubrepositories.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.databinding.FragmentMainListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainListFragment : Fragment(R.layout.fragment_main_list) {
    private val viewModel: MainListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainListBinding.bind(view)
    }
}
