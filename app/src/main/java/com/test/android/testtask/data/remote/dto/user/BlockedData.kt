package com.test.android.testtask.data.remote.dto.user

data class BlockedData(
    val blocked_from: String,
    val blocked_items: BlockedItems,
    val blocked_to: Any,
    val comment: String,
    val percent_fine_bonuses: Int,
    val reason: String,
    val type_blocked: Int
)