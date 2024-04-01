package com.example.testgitapiapp.data

import androidx.paging.PagingData
import com.example.testgitapiapp.models.User
import com.example.testgitapiapp.models.UsersList
import io.reactivex.rxjava3.core.Flowable

interface DataRepository{
    fun getUsersList(): Flowable<PagingData<UsersList>>

    suspend fun getUser(username: String): User
}