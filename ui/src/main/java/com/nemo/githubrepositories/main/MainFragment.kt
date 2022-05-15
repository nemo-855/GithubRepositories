package com.nemo.githubrepositories.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nemo.githubrepositories.R
import com.nemo.githubrepositories.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
    }
}