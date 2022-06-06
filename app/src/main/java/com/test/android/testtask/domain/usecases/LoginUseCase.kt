package com.test.android.testtask.domain.usecases

import com.test.android.testtask.core.Resource
import com.test.android.testtask.data.remote.dto.user.UserDTO
import com.test.android.testtask.domain.model.UserBio
import com.test.android.testtask.domain.model.UserCredentials
import com.test.android.testtask.domain.repository.Repository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: Repository) {

    suspend fun invoke(userCredentials: UserCredentials): Resource<UserBio> {
        return repository.login(userCredentials)
    }
}