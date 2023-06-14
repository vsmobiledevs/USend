package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.CreatePaymentResponse


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface PaymentApi {
        /**
        * CreatePayment
        * Create Payment
            * @param sourceId Card Nonce (required)
            * @param amount Amount (required)
            * @param orderId Order ID (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;CreatePaymentResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("payments/create_payment")
    fun createPayment(
        @retrofit2.http.Field("source_id") sourceId:String,
        @retrofit2.http.Field("amount") amount:Float,
        @retrofit2.http.Field("order_id") orderId:Int,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<CreatePaymentResponse>

}
