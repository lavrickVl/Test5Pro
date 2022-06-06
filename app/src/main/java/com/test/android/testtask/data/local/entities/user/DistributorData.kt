package com.test.android.testtask.data.local.entities.user

data class DistributorData(
    val brands: List<String>,
    val distributor_status: String,
    val distributor_vendor: String,
    val moderator_msg: String,
    val place_id: String,
    val street_address: String
)