package com.nemo.githubrepositories.main.list

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

suspend fun <T> Flow<T>.valueHistory(
    block: suspend CoroutineScope.() -> Unit
): List<T> {
    val list = mutableListOf<T>()
    coroutineScope {
        val job = launch {
            this@valueHistory.toList(list)
        }
        block.invoke(this)
        job.cancel()
    }
    return list
}
