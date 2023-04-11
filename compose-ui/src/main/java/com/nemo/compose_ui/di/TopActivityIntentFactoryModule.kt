package com.nemo.compose_ui.di

import android.content.Context
import android.content.Intent
import com.nemo.compose_ui.TopActivity
import com.nemo.githubrepositories.router.TopActivityIntentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TopActivityIntentFactoryModule {

    @Provides
    fun provideTopActivityIntentFactory(): TopActivityIntentFactory =
        object : TopActivityIntentFactory {
            override fun create(context: Context): Intent {
                return Intent(context, TopActivity::class.java)
            }
        }
}
