package com.nemo.githubrepositories.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nemo.githubrepositories.databinding.ActivityAndroidViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AndroidViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAndroidViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
