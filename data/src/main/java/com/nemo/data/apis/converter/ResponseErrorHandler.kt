package com.nemo.data.apis.converter

import com.nemo.data.apis.responses.GithubApiErrorResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import okhttp3.ResponseBody

fun ResponseBody.deserializeGithubApiErrorResponse(): GithubApiErrorResponse? {
    return when (val jsonString = this.string()) {
        null -> null
        else -> Json.decodeFromJsonElement(Json.parseToJsonElement(jsonString))
    }
}
