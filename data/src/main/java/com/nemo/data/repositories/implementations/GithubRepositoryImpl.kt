package com.nemo.data.repositories.implementations

import com.nemo.data.apis.RetrofitManager
import com.nemo.data.apis.converter.deserializeGithubApiErrorResponse
import com.nemo.data.apis.converter.toGithubProject
import com.nemo.data.model.GithubProject
import com.nemo.data.repositories.interfaces.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class GithubRepositoryImpl(
    private val retrofitManager: RetrofitManager
) : GithubRepository {
    override suspend fun fetchGithubProjects(userName: String): List<GithubProject> = withContext(Dispatchers.IO) {
        val response = retrofitManager.githubApi.fetchAllProjects(userName).awaitResponse()
        val body = response.body()

        return@withContext if (response.isSuccessful && body != null) {
            body.map { it.toGithubProject() }
        } else throw IllegalStateException(response.errorBody()?.deserializeGithubApiErrorResponse()?.message)
    }
}
