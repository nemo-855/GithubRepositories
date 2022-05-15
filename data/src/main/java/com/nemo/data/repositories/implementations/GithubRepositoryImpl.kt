package com.nemo.data.repositories.implementations

import com.nemo.data.apis.interfaces.GithubApi
import com.nemo.data.apis.responseconverters.deserializeGithubApiErrorResponse
import com.nemo.data.apis.responseconverters.toGithubProject
import com.nemo.data.models.GithubProject
import com.nemo.data.repositories.interfaces.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRepository {
    override suspend fun fetchGithubProjects(userName: String): List<GithubProject> = withContext(Dispatchers.IO) {
        val response = githubApi.fetchAllProjects(userName)
        val body = response.body()

        return@withContext if (response.isSuccessful && body != null) {
            body.map { it.toGithubProject() }
        } else throw IllegalStateException(response.errorBody()?.deserializeGithubApiErrorResponse()?.message)
    }
}
