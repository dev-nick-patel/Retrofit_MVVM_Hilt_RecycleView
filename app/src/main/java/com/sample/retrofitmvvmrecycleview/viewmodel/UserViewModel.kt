package com.sample.retrofitmvvmrecycleview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.retrofitmvvmrecycleview.model.User
import com.sample.retrofitmvvmrecycleview.repository.UserRepository
import com.sample.retrofitmvvmrecycleview.retrofit.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private var _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    fun getUsers(){
        viewModelScope.launch {
            val response = userRepository.getAllUsers()
            _users.postValue(response.body()?.users)
        }

    }
}