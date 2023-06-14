/*
 * USend API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * Contact: sumand.spaceo@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.base.network.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/**
* 
    * @param serviceName 
    * @param serviceCode 
    * @param carrierCode 
    * @param tracking 
    * @param shipmentCost 
    * @param deliveryTime 
    * @param image 
*/

@Parcelize
data class ShippingMethod (

    @SerializedName("service_name")
    var serviceName: String? = null,
    @SerializedName("service_code")
    var serviceCode: String? = null,
    @SerializedName("carrier_code")
    var carrierCode: String? = null,
    @SerializedName("tracking")
    var tracking: String? = null,
    @SerializedName("shipment_cost")
    var shipmentCost: BigDecimal? = null,
    @SerializedName("delivery_time")
    var deliveryTime: String? = null,
    @SerializedName("image")
    var image: String? = null
)
:Parcelable




