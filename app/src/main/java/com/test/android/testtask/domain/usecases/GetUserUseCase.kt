package com.test.android.testtask.domain.usecases

import com.test.android.testtask.core.Resource
import com.test.android.testtask.domain.model.UserBio
import com.test.android.testtask.domain.repository.Repository

class GetUserUseCase(private val repository: Repository) {

    suspend fun getUserBio(): UserBio {
        return repository.getUserBio()
    }


}