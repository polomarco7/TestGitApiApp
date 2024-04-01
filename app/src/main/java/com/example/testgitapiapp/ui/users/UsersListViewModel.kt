package com.example.testgitapiapp.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.testgitapiapp.data.DataRepositoryImpl
import com.example.testgitapiapp.models.UsersList
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val repository: DataRepositoryImpl
) : ViewModel() {

    fun getUsersList(): Flowable<PagingData<UsersList>> {
        return repository
            .getUsersList()
            .cachedIn(viewModelScope)
    }
}