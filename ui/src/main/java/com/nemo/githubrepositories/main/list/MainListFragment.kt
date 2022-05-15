package com.nemo.githubrepositories.main.list

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
        viewModel.uiModelListLD.observe(viewLifecycleOwner) { uiModelList ->
            adapter.submitList(uiModelList ?: return@observe)
        }
    }
}
