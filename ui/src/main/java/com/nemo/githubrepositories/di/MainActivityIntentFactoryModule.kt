package com.nemo.githubrepositories.di

import android.content.Context
import android.content.Intent
import com.nemo.githubrepositories.main.MainActivity
import com.nemo.githubrepositories.router.MainActivityIntentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MainActivityIntentFactoryModule {

    @Provides
    fun provideMainActivityIntentFactory(): MainActivityIntentFactory =
        object : MainActivityIntentFactory {
            override fun create(context: Context): Intent {
                return Intent(context, MainActivity::class.java)
            }
        }
}
