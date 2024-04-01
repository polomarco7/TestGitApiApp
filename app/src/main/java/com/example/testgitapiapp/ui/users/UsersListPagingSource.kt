package com.example.testgitapiapp.ui.users

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.testgitapiapp.data.DataApi
import com.example.testgitapiapp.models.UsersList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UsersListPagingSource @Inject constructor(
    private val repository: DataApi
): RxPagingSource<Int, UsersList>() {

    override fun getRefreshKey(state: PagingState<Int, UsersList>): Int = FIRST_PAGE

    private companion object {
        private const val FIRST_PAGE = 0
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, UsersList>> {
        val position = params.key ?: 0

        return repository.getUsersList(position)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: List<UsersList>, position: Int): LoadResult<Int, UsersList> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 0) null else position,
            nextKey = if (position == data.lastOrNull()?.id) null else data.lastOrNull()?.id
        )
    }
}