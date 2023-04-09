package com.nemo.githubrepositories.kmm.di

import com.nemo.githubrepositories_kmm.di.UseCaseModule
import com.nemo.githubrepositories_kmm.domain.GithubUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GithubUseCaseModule {

    @Provides
    fun provideGithubUseCase(): GithubUseCase {
        return UseCaseModule().provideGithubUseCase()
    }
}
