package com.sample.retrofitmvvmrecycleview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.retrofitmvvmrecycleview.model.User
import com.sample.retrofitmvvmrecycleview.repository.UserRepository
import com.sample.retrofitmvvmrecycleview.retrofit.ApiClient
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {

    private var _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    val userRepository by lazy {
        UserRepository(ApiClient.apiService)
    }

    fun getUsers(){
        viewModelScope.launch {
            val response = userRepository.getAllUsers()
            _users.postValue(response.body()?.users)
        }

    }
}