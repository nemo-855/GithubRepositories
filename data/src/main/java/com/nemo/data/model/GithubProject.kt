package com.nemo.data.model

import java.time.ZonedDateTime

class GithubProject(
    val id: Int,
    val name: String,
    val isPrivate: Boolean,
    val ownerName: String,
    val createdUnixTime: ZonedDateTime
)
