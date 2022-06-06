package com.test.android.testtask.data

import com.test.android.testtask.data.remote.dto.user.UserDTO
import com.test.android.testtask.data.remote.dto.user.UserLoginPost
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TestApi {

    @POST("login")
    suspend fun postUserLogin(@Body userLoginPost: UserLoginPost): Response<UserDTO>

}