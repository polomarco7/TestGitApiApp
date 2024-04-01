package com.example.testgitapiapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)

data class User(
    @Json(name = "avatar_url")val avatar: String? = null,
    val name: String? = null,
    val email: String? = null,
    val followers: Int = 0,
    val following: Int = 0,
    @Json(name = "created_at")val createdAt: String? = null,
    val company: String? = null
)
