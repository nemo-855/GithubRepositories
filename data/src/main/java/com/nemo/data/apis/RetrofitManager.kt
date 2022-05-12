package com.nemo.data.apis

import com.nemo.data.apis.Endpoints.githubApiEndpoint
import retrofit2.Retrofit

object RetrofitManager {
    private val retrofit = Retrofit.Builder()
        .baseUrl(githubApiEndpoint)
        .build()

    val githubApi: GithubApi = retrofit.create(GithubApi::class.java)
}
