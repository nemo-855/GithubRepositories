package com.nemo.githubrepositories.main.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nemo.githubrepositories.ui.R
import com.nemo.githubrepositories.ui.databinding.FragmentMainListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainListFragment : Fragment(R.layout.fragment_main_list) {
    private val viewModel: MainListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainListBinding.bind(view)

        val adapter = MainListAdapter()
        setUpRecyclerView(
            adapter = adapter,
            binding = binding
        )
        setUpSearchLayout(binding = binding)
        observeUiModelListLD(adapter = adapter)
    }

    private fun setUpRecyclerView(
        adapter: MainListAdapter,
        binding: FragmentMainListBinding,
    ) {
        binding.recyclerView.adapter = adapter
    }

    private fun setUpSearchLayout(binding: FragmentMainListBinding) {
        binding.searchLayout.setEndIconOnClickListener {
            viewModel.onClickSearchButton(
                userName = binding.inputEditText.text?.toString() ?: return@setEndIconOnClickListener
            )
        }
    }

    private fun observeUiModelListLD(adapter: MainListAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiModelListFlow.collect { uiModelList ->
                    adapter.submitList(uiModelList)
                }
            }
        }
    }
}
