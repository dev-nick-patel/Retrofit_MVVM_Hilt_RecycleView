package com.sample.retrofitmvvmrecycleview.repository

import com.sample.retrofitmvvmrecycleview.model.User
import com.sample.retrofitmvvmrecycleview.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllUsers(): Result<List<User>>{
        return  withContext(Dispatchers.IO){
            val results: Result<List<User>>
            val response = apiService.getAllUsers()
            response.apply {
                results = if (isSuccessful) {
                    Result.success(response.body()!!.users)
                }else
                    Result.failure(RuntimeException("Something went wrong"))
            }
            results
        }
    }

}