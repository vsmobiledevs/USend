package com.base.network.api

import com.base.network.model.CancelSubscriptionUsendResponse
import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.CreateTransactionResponse


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface SubscriptionsApi {


        /**
        * Create user transaction in In-app purchase
        * Create user transaction in In-app purchase
            * @param userType User type: android/ios/web (required)
            * @param authorization User Auth Token (optional)
            * @param base64Receipt base64 receipt for ios (optional)
            * @param purchaseSku purchase sku for android (optional)
            * @param purchaseToken purchase token for android (optional)
            * @param cardId Stripe Card Id (optional)
            * @param startDate Plan Start Date (optional)
            * @param endDate Plan End Date (optional)
            * @param amount Plan Amount (optional)
            * @param webSubscriptionId Web subscription Id (optional)
        * @return Call&lt;CreateTransactionResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("subscriptions/create_transaction")
    fun createTransaction(
            @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("user_type") userType:String,
        @retrofit2.http.Field("base64_receipt") base64Receipt:String?=null,
        @retrofit2.http.Field("purchase_sku") purchaseSku:String?=null,
        @retrofit2.http.Field("purchase_token") purchaseToken:String?=null,
        @retrofit2.http.Field("card_id") cardId:String?=null,
            @retrofit2.http.Field("plan_type") plan_type:String?=null,
        @retrofit2.http.Field("start_date") startDate:String?=null,
        @retrofit2.http.Field("end_date") endDate:String?=null,
        @retrofit2.http.Field("amount") amount:Float?=null,
        @retrofit2.http.Field("web_subscription_id") webSubscriptionId:String?=null):Call<CreateTransactionResponse>

    @retrofit2.http.FormUrlEncoded
    @POST("auto_shipment_details/cancel_subscription")
    fun cancelSubscriptionUsend(
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("user_id") userID:Int,
        @retrofit2.http.Field("charged_through_date") chargedThrough_date:String,
        @retrofit2.http.Field("subscription_id") webSubscriptionId:String,
    ):Call<CancelSubscriptionUsendResponse>

}
