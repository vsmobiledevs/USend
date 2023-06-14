package com.base.network.model

data class ShippingListResponse(
    val responseCode: Int,
    val responseData: ResponseData,
    val responseMessage: String
)