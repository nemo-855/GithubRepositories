package com.nemo.data.model

class GithubProject(
    val id: Int,
    val name: String,
    val isPrivate: Boolean,
    val ownerName: String,
    val createdUnixTime: Long
)
