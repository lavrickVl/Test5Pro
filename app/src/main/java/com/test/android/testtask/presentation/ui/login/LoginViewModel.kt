package com.test.android.testtask.presentation.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.android.testtask.R
import com.test.android.testtask.core.Constants
import com.test.android.testtask.core.Constants.TAG
import com.test.android.testtask.core.Resource
import com.test.android.testtask.core.UIEvent
import com.test.android.testtask.domain.User
import com.test.android.testtask.domain.model.UserCredentials
import com.test.android.testtask.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    val _isLogged = User._isLogged

    private val _eventFlow: MutableSharedFlow<UIEvent> = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun login(userCredentials: UserCredentials) = viewModelScope.launch(Dispatchers.IO) {
        _eventFlow.emit(UIEvent.Loading())
        val result = loginUseCase.invoke(userCredentials)

        if (result is Resource.Success){
            User._isLogged = true
            _eventFlow.emit(UIEvent.Toast("Authentication is succeeded"))
            delay(2000L)
            _eventFlow.emit(UIEvent.Loading(false))
            _eventFlow.emit(UIEvent.NavigateTo(R.id.action_loginFragment_to_profileFragment))
        } else{
            _eventFlow.emit(UIEvent.SnackBar(result.message ?: "Unauthorized"))
        }
    }
}
