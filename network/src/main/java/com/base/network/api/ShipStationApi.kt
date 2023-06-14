package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import java.math.BigDecimal
import com.base.network.model.ListGetRatesResponse
import com.base.network.model.ListshippingCalculatorRates
import com.base.network.model.ShippingListResponse


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface ShipStationApi {
        /**
        * Get carrier rate list
        * Get carrier rate list
            * @param addressId Address Id (required)
            * @param packageId Package ids - Pass comma seperated ids (required)
            * @param authorization User Auth Token (optional)
            * @param carrierCode Carrier Code - Pass comma seperated Carrier Code (optional)
            * @param priceFilter Shipment Cost Filter (optional)
        * @return Call&lt;ListGetRatesResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("carriers/rates_list")
    fun getCarrierRateList(
        @retrofit2.http.Field("address_id") addressId:Int,
        @retrofit2.http.Field("package_id") packageId:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("carrier_code") carrierCode:String?=null,
        @retrofit2.http.Field("price_filter") priceFilter:String?=null
        ):Call<ListGetRatesResponse>
     /**
  * Get shipping list*/
    @GET("carriers/get_carriers_list")
    fun getShippingList():Call<ShippingListResponse>

        /**
        * Shipping calculator rates
        * Shipping calculator rates
            * @param countryCodeId Country Id (required)
            * @param postalCode Zipcode (required)
            * @param weight Weight (required)
            * @param weightUnit Weight unit (required)
            * @param length Package Length (required)
            * @param width Package Width (required)
            * @param height Package Height (required)
            * @param dimensionUnit Dimension unit (required)
            * @param city City (optional)
            * @param carrierCode Carrier Code - Pass comma seperated Carrier Code (optional)
            * @param priceFilter Shipment Cost Filter (optional)
        * @return Call&lt;ListshippingCalculatorRates&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("carriers/shipping_calculator")
    fun shippingCalculatorRates(
        @retrofit2.http.Field("country_code_id") countryCodeId:Int,
        @retrofit2.http.Field("postal_code") postalCode:String,
        @retrofit2.http.Field("weight") weight:BigDecimal,
        @retrofit2.http.Field("weight_unit") weightUnit:String,
        @retrofit2.http.Field("length") length:BigDecimal,
        @retrofit2.http.Field("width") width:BigDecimal,
        @retrofit2.http.Field("height") height:BigDecimal,
        @retrofit2.http.Field("dimension_unit") dimensionUnit:String,
        @retrofit2.http.Field("city") city:String?=null,
        @retrofit2.http.Field("carrier_code") carrierCode:String?=null,
        @retrofit2.http.Field("price_filter") price_filter:String?=null,
        @retrofit2.http.Field("single_carrier_code") single_carrier_code:String?=null
        ): Call<ListshippingCalculatorRates>

}
