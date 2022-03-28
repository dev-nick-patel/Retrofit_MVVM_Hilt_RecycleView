package com.sample.retrofitmvvmrecycleview.di

import com.sample.retrofitmvvmrecycleview.retrofit.ApiService
import com.sample.retrofitmvvmrecycleview.utils.Constants.BASE_URL
import com.sample.retrofitmvvmrecycleview.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule : Util() {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}