package com.test.android.testtask.presentation.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.test.android.testtask.domain.model.UserBio
import com.test.android.testtask.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val userBio: LiveData<UserBio> = liveData(Dispatchers.IO) {
        val userBio = getUserUseCase.getUserBio()
        emit(userBio)
    }
}