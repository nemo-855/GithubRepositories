package com.nemo.data.models

import java.time.ZonedDateTime

class GithubProject(
    val id: Int,
    val name: String,
    val isPrivate: Boolean,
    val ownerName: String,
    val createdUnixTime: ZonedDateTime
)
