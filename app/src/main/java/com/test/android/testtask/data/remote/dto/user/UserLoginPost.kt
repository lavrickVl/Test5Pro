package com.test.android.testtask.data.remote.dto.user

data class UserLoginPost(
    val phone_code: String,
    val phone_number: String,
    val password: String,
)
