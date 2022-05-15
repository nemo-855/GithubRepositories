package com.nemo.githubrepositories.main

import androidx.lifecycle.ViewModel
import com.nemo.data.repositories.interfaces.GithubRepository

class MainListViewModel(
    private val githubRepository: GithubRepository
) : ViewModel()
