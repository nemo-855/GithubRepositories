package com.nemo.data.apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{userName}/repos")
    suspend fun fetchAllProjects(
        @Path("userName") userName: String
    ): Call<FetchAllProjectsResponse>
}
