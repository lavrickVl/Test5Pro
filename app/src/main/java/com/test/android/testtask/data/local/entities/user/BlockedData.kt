package com.test.android.testtask.data.local.entities.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BlockedData(
    @PrimaryKey val id: Long,
    val blocked_from: String,
//    val blocked_items: BlockedItems,
//    val blocked_to: Any,
    val comment: String,
    val percent_fine_bonuses: Int,
    val reason: String,
    val type_blocked: Int
)