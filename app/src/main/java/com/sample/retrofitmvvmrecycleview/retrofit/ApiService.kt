package com.sample.retrofitmvvmrecycleview.retrofit

import com.sample.retrofitmvvmrecycleview.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("getUsers.php")
    suspend fun getAllUsers(): Response<UserResponse>
}