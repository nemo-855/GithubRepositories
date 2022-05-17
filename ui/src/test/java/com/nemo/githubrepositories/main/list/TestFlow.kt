package com.nemo.githubrepositories.main.list

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TestFlow<T>(
    flow: Flow<T>,
    scope: CoroutineScope
) {
    private val _history = mutableListOf<T>()
    val history: List<T> = _history
    private val job: Job

    init {
        job = flow.onEach { _history.add(it) }
            .launchIn(scope)
    }

    fun close() {
        job.cancel()
    }

    fun getHistoryWithClose(): List<T> {
        close()
        return history
    }
}

fun <T> Flow<T>.toTest(scope: CoroutineScope): TestFlow<T> =
    TestFlow(this, scope)
