package com.nemo.compose_ui.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatTime(format: String): String? {
    val dateFormatter = DateTimeFormatter.ofPattern(format)
    return this.toJavaLocalDateTime().format(dateFormatter)
}