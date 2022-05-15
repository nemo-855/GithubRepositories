package com.nemo.data.repositories.interfaces

import com.nemo.data.models.GithubProject
import com.nemo.data.repositories.implementations.GithubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface GithubRepositoryModule {

    @Binds
    @Singleton
    fun bindGithubRepository(
        githubRepositoryImpl: GithubRepositoryImpl
    ): GithubRepository
}

interface GithubRepository {
    suspend fun fetchGithubProjects(userName: String): List<GithubProject>
}
