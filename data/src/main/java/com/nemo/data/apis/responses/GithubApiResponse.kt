package com.nemo.data.apis.responses

import kotlinx.serialization.Serializable

class FetchAllProjectsResponse(
    val id: Int,
    val name: String,
    val private: Boolean,
    val owner: OwnerResponse,
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
