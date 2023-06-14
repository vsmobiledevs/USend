package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import java.math.BigDecimal
import com.base.network.model.GetOrderResponse
import com.base.network.model.OrderListResponse
import com.base.network.model.OrderResponse


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface OrderApi {
        /**
        * Create Order
        * Create Order
            * @param addressId Address ID (required)
            * @param packageId Package ids - Pass comma seperated ids (required)
            * @param amountPaid Total Charges of Order (required)
            * @param shippingAmount Shipping amount (required)
            * @param additionalFee Additional fees (required)
            * @param consolidatedFees consolidated fees (required)
            * @param requesteShippingService Shipping Service Name (required)
            * @param carrierCode carrierCode (stamps_com/fedex/dhl_express) (required)
            * @param serviceCode Service Code (required)
            * @param cardId Stripe Card Id (required)
            * @param authorization User Auth Token (optional)
            * @param additionalChargeId Additional Charge ids - Pass comma seperated ids (optional)
            * @param notes Notes (optional)
            * @param declaredValue Declared value (optional)
            * @param packageContent Content of package (optional)
        * @return Call&lt;OrderResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("orders")
    fun createOrder(
        @retrofit2.http.Field("address_id") addressId:Int,
        @retrofit2.http.Field("package_id") packageId:String,
        @retrofit2.http.Field("amount_paid") amountPaid:String,
        @retrofit2.http.Field("shipping_amount") shippingAmount:String,
        @retrofit2.http.Field("additional_fee") additionalFee:String,
        @retrofit2.http.Field("consolidated_fees") consolidatedFees:BigDecimal,
        @retrofit2.http.Field("requeste_shipping_service") requesteShippingService:String,
        @retrofit2.http.Field("carrier_code") carrierCode:String,
        @retrofit2.http.Field("service_code") serviceCode:String,
        @retrofit2.http.Field("card_id") cardId:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("additional_charge_id") additionalChargeId:String?=null,
        @retrofit2.http.Field("notes") notes:String?=null,
        @retrofit2.http.Field("declared_value") declaredValue:String?=null,
        @retrofit2.http.Field("package_content") packageContent:String?=null
        ):Call<OrderResponse>

        /**
        * Get Order
        * Get Order
            * @param id Order ID (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;GetOrderResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("orders/{id}")
    fun getOrder(
        @retrofit2.http.Path("id") id:Int,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<GetOrderResponse>
        /**
        * Order List
        * Order List
            * @param authorization User Auth Token (optional)
        * @return Call&lt;OrderListResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @POST("orders/order_list")
    fun orderList(
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<OrderListResponse>

        /**
        * Update Order and Payment
        * 
            * @param orderId Order Id (required)
            * @param paymentId Payment Id (required)
            * @param paymentStatus Payment Status (required)
            * @param paymentIntentId Stripe payment intent id (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;OrderListResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("orders/update_order_and_payment")
    fun updateOrderAndPayment(
        @retrofit2.http.Field("order_id") orderId:String,
        @retrofit2.http.Field("payment_id") paymentId:String,
        @retrofit2.http.Field("payment_status") paymentStatus:String,
        @retrofit2.http.Field("payment_intent_id") paymentIntentId:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<OrderListResponse>

}
