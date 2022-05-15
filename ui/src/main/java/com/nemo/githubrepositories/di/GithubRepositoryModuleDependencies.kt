package com.nemo.githubrepositories.di

import com.nemo.data.repositories.interfaces.GithubRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@EntryPoint
@InstallIn(SingletonComponent::class)
interface GithubRepositoryModuleDependencies {

    @Singleton
    fun githubRepository(): GithubRepository
}
