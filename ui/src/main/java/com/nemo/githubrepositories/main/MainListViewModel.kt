package com.nemo.githubrepositories.main

import androidx.lifecycle.ViewModel
import com.nemo.data.repositories.interfaces.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel()
