package com.nemo.data.apis.responses

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
