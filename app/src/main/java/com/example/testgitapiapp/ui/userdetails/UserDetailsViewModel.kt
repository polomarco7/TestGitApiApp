package com.example.testgitapiapp.ui.userdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testgitapiapp.data.DataRepositoryImpl
import com.example.testgitapiapp.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val repository: DataRepositoryImpl
): ViewModel() {
    private val _contactsData = MutableStateFlow(User())
    val contactsData: StateFlow<User> = _contactsData.asStateFlow()

    fun getUserInfo(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.getUser(userName)
            }.fold(
                onSuccess = { _contactsData.value = it },
                onFailure = { Log.d("ViewModel", it.message ?: "") }
            )
        }
    }

}