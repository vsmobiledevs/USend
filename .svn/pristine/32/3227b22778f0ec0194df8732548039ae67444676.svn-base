package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.ConciergeRequestResponse
import com.base.network.model.GetConciergeRequestResponse
import com.base.network.model.ListConciergeRequestResponse


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface ConciergeRequestApi {
        /**
        * Concierge Request Details
        * New Concierge Request
            * @param conciergeId Concierge Id (required)
        * @return Call&lt;GetConciergeRequestResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("concierge_requests/concierge_requests_details")
    fun conciergeRequestDetails(
        @retrofit2.http.Field("concierge_id") conciergeId:Int
        ):Call<GetConciergeRequestResponse>

        /**
        * List Concierge Requests
        * List Concierge Requests
            * @param authorization User Auth Token (optional)
        * @return Call&lt;ListConciergeRequestResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("concierge_requests")
    fun listConciergeRequest(
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<ListConciergeRequestResponse>

        /**
        * New Concierge Request
        * New Concierge Request
            * @param productName Product Name (required)
            * @param productImage Product Image - Image Name Comma Seperated (required)
            * @param websiteUrl Website URL (required)
            * @param price Price (required)
            * @param description Description (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;ConciergeRequestResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("concierge_requests")
    fun newConciergeRequest(
        @retrofit2.http.Field("product_name") productName:String,
        @retrofit2.http.Field("product_image") productImage:String,
        @retrofit2.http.Field("website_url") websiteUrl:String,
        @retrofit2.http.Field("price") price:Float,
        @retrofit2.http.Field("description") description:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<ConciergeRequestResponse>

        /**
        * Update Concierge Status
        * 
            * @param conciergeId Concierge Id (required)
            * @param paymentIntentId Stripe payment intent id (required)
        * @return Call&lt;GetConciergeRequestResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("concierge_requests/update_status")
    fun updateConciergeStatus(
        @retrofit2.http.Field("concierge_id") conciergeId:Int,
        @retrofit2.http.Field("payment_intent_id") paymentIntentId:String
        ):Call<GetConciergeRequestResponse>

}
