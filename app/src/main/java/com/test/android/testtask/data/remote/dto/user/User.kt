package com.test.android.testtask.data.remote.dto.user

data class User(
    val api_key: String,
    val app_brand_id: Int,
    val avatar: String?,
    val blocked_data: BlockedData,
    val chat_id: String,
    val distributor_data: DistributorData,
    val firebase_id: String,
    val location_type: String?,
    val name: String,
    val payout_reject_msg: String?,
    val payout_status: Int,
    val phone_code: String,
    val phone_number: String,
    val place: String?,
    val referral: String?,
    val referral_bonus: Int,
    val second_name: String,
    val sector_type: SectorType,
    val solar_staff: Boolean,
    val type_blocked: Int,
    val userBalance: UserBalance,
    val user_id: Int,
    val user_type: Int
)