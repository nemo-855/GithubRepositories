package com.nemo.data.apis.responseconverters

import com.nemo.data.apis.responses.FetchAllProjectsResponse
import com.nemo.data.models.GithubProject
import java.time.ZoneId
import java.time.ZonedDateTime

fun FetchAllProjectsResponse.toGithubProject() = GithubProject(
    id = id,
    name = name,
    isPrivate = private,
    ownerName = owner.login,
    createdTime = ZonedDateTime.parse(createdAt).withZoneSameInstant(ZoneId.of("Asia/Tokyo")),
)
