package com.example.testgitapiapp.data

import com.example.testgitapiapp.models.User
import com.example.testgitapiapp.models.UsersList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataApi {
    @GET("/users")
    fun getUsersList(@Query("since") sinceId: Int): Single<List<UsersList>>
    @GET("/users/{username}")
    suspend fun getUser(@Path("username") userName: String): User
}