package com.example.testgitapiapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.testgitapiapp.models.User
import com.example.testgitapiapp.models.UsersList
import com.example.testgitapiapp.ui.users.UsersListPagingSource
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val repository: DataApi): DataRepository {

    override fun getUsersList(): Flowable<PagingData<UsersList>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { UsersListPagingSource(repository) }
        ).flowable
    }

    override suspend fun getUser(username: String): User {
        return repository.getUser(username)
    }
}