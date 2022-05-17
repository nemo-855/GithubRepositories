package com.nemo.githubrepositories.main.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.databinding.FragmentMainListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainListFragment : Fragment(R.layout.fragment_main_list) {
    private val viewModel: MainListViewModel by viewModels()

    private var _binding: FragmentMainListBinding? = null
    private val binding: FragmentMainListBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainListBinding.bind(view)

        val adapter = MainListAdapter()
        setUpRecyclerView(adapter)
        setUpSearchLayout()
        observeUiModelListLD(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView(adapter: MainListAdapter) {
        binding.recyclerView.adapter = adapter
    }

    private fun setUpSearchLayout() {
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
