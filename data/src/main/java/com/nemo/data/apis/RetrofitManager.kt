package com.nemo.data.apis

import com.nemo.data.apis.interfaces.GithubApi
import retrofit2.Retrofit

object RetrofitManager {
    private const val githubApiEndpoint = "https://api.github.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(githubApiEndpoint)
        .build()

    val githubApi: GithubApi = retrofit.create(GithubApi::class.java)
}
