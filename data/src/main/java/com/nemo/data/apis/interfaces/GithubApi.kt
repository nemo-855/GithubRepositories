package com.nemo.data.apis.interfaces

import com.nemo.data.apis.EndPoints
import com.nemo.data.apis.responses.FetchAllProjectsResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

@Module
@InstallIn(SingletonComponent::class)
object GithubApiModule {

    @Provides
    fun provideGithubApi(): GithubApi = Retrofit.Builder()
        .baseUrl(EndPoints.githubApiEndpoint)
        .build()
        .create(GithubApi::class.java)
}

interface GithubApi {
    @GET("users/{userName}/repos")
    suspend fun fetchAllProjects(
        @Path("userName") userName: String
    ): Call<List<FetchAllProjectsResponse>>
}
