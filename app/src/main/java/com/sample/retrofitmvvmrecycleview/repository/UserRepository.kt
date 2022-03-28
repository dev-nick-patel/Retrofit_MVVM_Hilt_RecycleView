package com.sample.retrofitmvvmrecycleview.repository

import com.sample.retrofitmvvmrecycleview.model.UserResponse
import com.sample.retrofitmvvmrecycleview.retrofit.ApiClient
import com.sample.retrofitmvvmrecycleview.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

//    val apiService by lazy {
//        ApiClient.apiService
//    }

   suspend fun getAllUsers(): Response<UserResponse> {
      return  withContext(Dispatchers.IO){
            val response = apiService.getAllUsers()
          response
        }
    }

//    suspend fun getAllUsers() = apiService.getAllUsers()
}