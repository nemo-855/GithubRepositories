package com.nemo.data.apis.responses

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

class FetchAllProjectsResponse(
    val id: Int,
    val name: String,
    val private: Boolean,
    val owner: OwnerResponse,
    @Json(name = "created_at")
    val createdAt: String
)

class OwnerResponse(
    val login: String
)

@Serializable
class GithubApiErrorResponse(
    val message: String,
    val documentationUrl: String
)
