package com.nemo.data.apis.converter

import com.nemo.data.apis.responses.FetchAllProjectsResponse
import com.nemo.data.model.GithubProject
import java.time.ZoneId
import java.time.ZonedDateTime

fun FetchAllProjectsResponse.toGithubProject() = GithubProject(
    id = id,
    name = name,
    isPrivate = private,
    ownerName = owner.login,
    createdUnixTime = ZonedDateTime.parse(createdAt).withZoneSameInstant(ZoneId.of("Asia/Tokyo")),
)
