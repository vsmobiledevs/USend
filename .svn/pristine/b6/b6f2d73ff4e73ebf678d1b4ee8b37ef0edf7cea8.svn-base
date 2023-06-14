package com.base.network.api

import com.base.network.model.*
import retrofit2.Call
import retrofit2.http.*


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface UsendCardApi {

    /**
     * Add Card  USend api
     * Add Card
     * @param stripeSourceToken Square up Source Token (required)
     * @param authorization User Auth Token (required)
     *  @param content-Type  (required)
     * @return Call&lt;AddSqaureup cardResponse&gt;
     */

    @POST("auto_shipment_details/create_auto_shipment")
    @Headers(
        "Content-Type: application/json"
    )
    fun addUsendCard(
        @Header("Authorization") authorization:String?=null,
        @Body addCardUsendList: AddCardUsend
    ):Call<UsendAddResponse>


    /**
     * Squareup Card Delete
     * Squareup Card Delete
     * @param cardId Squareup Card Id (required)
     * @param authorization User Auth Token (optional)
     * @return Call&lt;SquareupCardDeleteResponse&gt;
     */
    @retrofit2.http.FormUrlEncoded
    @POST("payment_details/delete_stripe_card")
    fun squareUsendCardDelete(
        @retrofit2.http.Field("card_id") cardId:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null
    ):Call<UsenCardDeleteResponse>

    /**
     * Squareup Card Delete
     * Squareup Card Delete
     * @param cardId Squareup Card Id (required)
     * @param authorization User Auth Token (optional)
     * @return Call&lt;SquareupCardDeleteResponse&gt;
     */
    @retrofit2.http.FormUrlEncoded
    @POST("users/create_squareup_logger")
    fun logsUsendCardMention(
        @retrofit2.http.Field("request") request:String,
        @retrofit2.http.Field("response") response:String,
        @retrofit2.http.Field("type_of_request") type_of_request:String,
        @retrofit2.http.Field("user_id") user_id:String?=null
    ):Call<UsenCardDeleteResponse>






}
