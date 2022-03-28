package com.sample.retrofitmvvmrecycleview.utils

import okhttp3.OkHttpClient

open class Util {

    val httpClient: OkHttpClient by lazy {
        OkHttpClient()
            .newBuilder()
            .build()
    }

    companion object {
        const val BASE_URL = "https://dev.codingambitions.com/android/ecommerce/api/v1/"
    }
}