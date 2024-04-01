package com.example.testgitapiapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersList(
    val id: Int? = null,
    val login: String = "",
    @Json(name = "avatar_url")val avatarUrl: String = ""
)