package com.nemo.githubrepositories

import android.app.Application
import com.nemo.githubrepositories_kmm.di.init
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubRepositoriesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }
}
