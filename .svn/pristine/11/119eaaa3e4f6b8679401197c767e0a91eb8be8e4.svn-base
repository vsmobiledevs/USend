package com.base.network.api

import com.base.network.model.*
import retrofit2.Call
import retrofit2.http.*


import java.math.BigDecimal


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface ShippingAddressApi {
        /**
        * Create Shipping Address
        * Create Shipping Address
            * @param label Label (required)
            * @param countryCodeId Country Id (required)
            * @param phone Phone No (required)
            * @param city City (required)
            * @param street1 Address line 1 (required)
            * @param street2 Address line 2 (required)
            * @param postalCode Zipcode (required)
            * @param receiverName Receiver name (required)
            * @param isBillingAddress same as biling address (required)
            * @param authorization User Auth Token (optional)
            * @param state State (optional)
        * @return Call&lt;CreateAddressResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("addresses/add_shipping_address")
    fun createShippingAddresses(
        @retrofit2.http.Field("label") label:String,
        @retrofit2.http.Field("country_code_id") countryCodeId:Int,
        @retrofit2.http.Field("phone") phone:String,
        @retrofit2.http.Field("city") city:String,
        @retrofit2.http.Field("street_1") street1:String,
        @retrofit2.http.Field("street_2") street2:String,
        @retrofit2.http.Field("postal_code") postalCode:String,
        @retrofit2.http.Field("receiver_name") receiverName:String,
        @retrofit2.http.Field("is_billing_address") isBillingAddress:Boolean,
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("state") state:String?=null
        ):Call<CreateAddressResponse>

        /**
        * Delete Shipping Address
        * Delete Shipping Address
            * @param id Address ID (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SuccessModel&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @DELETE("addresses/delete_address")
    fun deleteShippingAddress(
        @retrofit2.http.Query("id") id:Int,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SuccessModel>

        /**
        * Edit Shipping Address
        * Edit Shipping Address
            * @param id Address ID (required)
            * @param label Label (required)
            * @param countryCodeId Country Id (required)
            * @param phone Phone No (required)
            * @param city City (required)
            * @param street1 Address line 1 (required)
            * @param street2 Address line 2 (required)
            * @param postalCode Zipcode (required)
            * @param receiverName Receiver name (required)
            * @param authorization User Auth Token (optional)
            * @param state State (optional)
            * @param isBillingAddress same as biling address (optional)
        * @return Call&lt;CreateAddressResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("addresses/update_address")
    fun editShippingAddresses(
        @retrofit2.http.Query("id") id:Int,
        @retrofit2.http.Field("label") label:String,
        @retrofit2.http.Field("country_code_id") countryCodeId:Int,
        @retrofit2.http.Field("phone") phone:String,
        @retrofit2.http.Field("city") city:String,
        @retrofit2.http.Field("street_1") street1:String,
        @retrofit2.http.Field("street_2") street2:String,
        @retrofit2.http.Field("postal_code") postalCode:String,
        @retrofit2.http.Field("receiver_name") receiverName:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("state") state:String?=null,
        @retrofit2.http.Field("is_billing_address") isBillingAddress:Boolean?=null
        ):Call<EditShippingAddressReponse>

        /**
        * Additional Service Charge
        * Additional Service Charge
        * @return Call&lt;GetAdditionalServiceChargeResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("addresses/get_additional_services_charge")
    fun getAdditionalServiceCharge(
        
        ):Call<GetAdditionalServiceChargeResponse>

        /**
        * Get Address
        * Get Address
            * @param addressId ID of address (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;GetAddressResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("addresses/get_address")
    fun getAddress(
        @retrofit2.http.Query("address_id") addressId:Int,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<GetAddressResponse>

        /**
        * Shipping Address List
        * Shipping Address List
            * @param authorization User Auth Token (optional)
        * @return Call&lt;ListAddressResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("addresses")
    fun shippingAddressList(
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<ListAddressResponse>

        /**
        * Shipping Details
        * Shipping Details
            * @param id Address Id (required)
            * @param packageId Package ids - Pass comma seperated ids (required)
            * @param serviceName Service Name (required)
            * @param serviceCode Service Code (required)
            * @param carrierCode Carrier Code (required)
            * @param shipmentCost Shipment Cost (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;ShippingDetailsResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("addresses/shipping_details")
    fun shippingDetails(
        @retrofit2.http.Field("id") id:Int,
        @retrofit2.http.Field("package_id") packageId:String,
        @retrofit2.http.Field("service_name") serviceName:String,
        @retrofit2.http.Field("service_code") serviceCode:String,
        @retrofit2.http.Field("carrier_code") carrierCode:String,
        @retrofit2.http.Field("shipment_cost") shipmentCost:BigDecimal,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<ShippingDetailsResponse>

}
