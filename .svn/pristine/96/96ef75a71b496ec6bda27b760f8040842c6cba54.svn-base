package com.base.network.api

import com.base.network.model.*
import retrofit2.Call
import retrofit2.http.*

interface
SquareUpCardApi {
    /**
     * Add Card
     * Add Card

     * @param authorization Squareup Auth Token (required)
     *  @param content-Type  (required)
     * @return Call&lt;AddSqaureup cardResponse&gt;
     */
    //  @retrofit2.http.FormUrlEncoded

    @POST("v2/cards")
    @Headers(
        "Content-Type: application/json"
    )
    fun addSquareUpCard(
        @Header("Authorization") authorization:String?=null,
        @Body createCardList: CreateCard
    ): Call<SquareupCreateResponse>

    /**

     * List Card

     * @param authorization Squareup Auth Token (required)
     *  @param content-Type  (required)
     * @return Call&lt;AddSqaureup cardResponse&gt;
     */
    //  @retrofit2.http.FormUrlEncoded

    @GET("v2/cards")
    @Headers(
        "Content-Type: application/json"
    )
    fun listSquareUpCard(
        @Header("Authorization") authorization:String?=null,
        @Query("customer_id") customer_id:String)
       :Call<SquareUpCardList>



    @POST("v2/cards/{card_id}/disable")
    @Headers(
        "Content-Type: application/json"
    )
    fun deleteSquareUpCard(
        @Header("Authorization") authorization:String?=null,
        @Path("card_id") card_id:String)
            :Call<Squareupdelete>

    /**
     * Create Subscription
     * @param authorization Squareup Auth Token (required)
     *  @param content-Type  (required)
     *  @param Body  (required)
     * @return Call&lt;AddSqaureup cardResponse&gt;
     */

    @POST("v2/subscriptions")
    @Headers(
        "Content-Type: application/json"
    )
    fun createSubscriptions(
        @Header("Authorization") authorization:String?=null,
        @Body createCardList: CreateSubscription
    ): Call<SubscriptionResponse>
    /**
     * Retrive Subscription
     * @param authorization Squareup Auth Token (required)
     *  @param content-Type  (required)
     *  @param Path   SubscriptionID(required)
     * @return Call&lt;AddSqaureup cardResponse&gt;
     */

    @GET("v2/subscriptions/{subscription_id}")
    @Headers(
        "Content-Type: application/json"
    )
    fun retriveSubscriptions(
        @Header("Authorization") authorization:String?=null,
        @Path("subscription_id") subscription_id:String
    ): Call<SubscriptionResponse>
    /**
     * Cancel Subscription
     * @param authorization Squareup Auth Token (required)
     *  @param content-Type  (required)
     *  @param Path   SubscriptionID(required)
     * @return Call&lt;AddSqaureup cardResponse&gt;
     */
    @POST("v2/subscriptions/{subscription_id}/cancel")
    @Headers(
        "Content-Type: application/json")
    fun cancelSubscriptions(
        @Header("Authorization") authorization:String?=null,
        @Path("subscription_id") subscription_id:String
    ): Call<PlanCancelresponse>


}