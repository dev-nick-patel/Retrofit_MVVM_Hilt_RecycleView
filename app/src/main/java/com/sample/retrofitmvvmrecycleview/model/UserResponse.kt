package com.sample.retrofitmvvmrecycleview.model

data class UserResponse(
    val message: String = "",
    val res: String = "",
    val users: List<User> = listOf()
)