package com.nemo.githubrepositories.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubRepositoriesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //FIXME: これをどうにかして呼び出す
        //initDi()
    }
}
