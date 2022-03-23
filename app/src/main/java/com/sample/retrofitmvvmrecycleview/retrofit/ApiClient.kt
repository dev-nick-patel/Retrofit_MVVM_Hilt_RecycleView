package com.sample.retrofitmvvmrecycleview.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val BASE_URL = "https://dev.codingambitions.com/android/ecommerce/api/v1/"

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}