package com.nemo.githubrepositories.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun ZonedDateTime.formatTime(format: String): String? {
    val dateFormatter = DateTimeFormatter.ofPattern(format)
    return this.format(dateFormatter)
}
