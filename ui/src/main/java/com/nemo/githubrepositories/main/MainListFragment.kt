package com.nemo.githubrepositories.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.databinding.FragmentMainListBinding

class MainListFragment : Fragment(R.layout.fragment_main_list) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainListBinding.bind(view)
    }
}
