package com.test.android.testtask.data.remote.dto.user

data class UserBalance(
    val balance: Int,
    val balance_on_moderation: Int,
    val balance_temp: Int,
    val sales_balance_accepted: Int,
    val sales_balance_moderated: Int,
    val sales_balance_send: Int,
    val total_payment_request: Int
)