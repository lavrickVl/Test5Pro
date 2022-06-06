package com.test.android.testtask.domain.repository

import com.test.android.testtask.core.Resource
import com.test.android.testtask.domain.model.UserBio
import com.test.android.testtask.domain.model.UserCredentials

interface Repository {

    suspend fun login(userCredentials: UserCredentials): Resource<UserBio>

    suspend fun getUserBio(): UserBio
}