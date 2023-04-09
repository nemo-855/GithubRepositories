package com.nemo.githubrepositories.router

import android.content.Context
import android.content.Intent

interface MainActivityIntentFactory {
    fun create(context: Context): Intent
}
