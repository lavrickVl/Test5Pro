package com.test.android.testtask.core

sealed class UIEvent () {
    data class Loading(val isLoading: Boolean = true) : UIEvent()
    data class Toast(val msg: String, val isLoading: Boolean = false) : UIEvent()
    data class SnackBar(val msg: String, var isLoading: Boolean = false) : UIEvent()

    data class NavigateTo(val navigationAction: Int, val isLoading: Boolean = false) : UIEvent()
}
