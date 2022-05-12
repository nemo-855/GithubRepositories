package com.nemo.data.repositories

import com.nemo.data.model.GithubProject

interface GithubRepository {
    suspend fun fetchGithubProjects(): List<GithubProject>
}
