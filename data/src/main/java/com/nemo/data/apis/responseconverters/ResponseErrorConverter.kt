package com.nemo.data.apis.responseconverters

import com.nemo.data.apis.responses.GithubApiErrorResponse
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import okhttp3.ResponseBody

fun ResponseBody.deserializeGithubApiErrorResponse(): GithubApiErrorResponse {
    return Json.decodeFromJsonElement(Json.parseToJsonElement(this.string()))
}
