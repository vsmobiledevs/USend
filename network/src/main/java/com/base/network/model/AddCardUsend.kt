package com.base.network.model

data class AddCardUsend(
    val brand: String,
    val card_id: String,
    val created_at: String,
    val cvv: String,
    val expiry_month: Int,
    val expiry_year: Int,
    val fingerprint: String,
    val last_4: String,
    val updated_at: String,
    val user_id: String
)