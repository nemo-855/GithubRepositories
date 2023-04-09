package com.nemo.githubrepositories.app

import android.app.Application
import com.nemo.githubrepositories_kmm.di.initDi
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubRepositoriesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDi()
    }
}
