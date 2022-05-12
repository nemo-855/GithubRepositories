package com.nemo.data.repositories.interfaces

import com.nemo.data.model.GithubProject

interface GithubRepository {
    suspend fun fetchGithubProjects(): List<GithubProject>
}