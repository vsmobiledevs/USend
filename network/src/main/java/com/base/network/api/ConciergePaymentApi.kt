package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.CreatePaymentResponse


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface ConciergePaymentApi {
        /**
        * CreatePayment
        * Create Payment
            * @param cardId Stripe Card Id (required)
            * @param payableAmount Amount to be paid (required)
            * @param conciergeId Concierge ID (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;CreatePaymentResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("payments/create_payment")
    fun createPayment(
        @retrofit2.http.Field("card_id") cardId:String,
        @retrofit2.http.Field("payable_amount") payableAmount:Float,
        @retrofit2.http.Field("concierge_id") conciergeId:Int,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<CreatePaymentResponse>

}
