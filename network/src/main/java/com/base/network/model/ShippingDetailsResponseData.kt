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

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/**
* 
    * @param shippingAddress 
    * @param shippingMethod 
    * @param invoiceDetails 
*/

@Parcelize
data class ShippingDetailsResponseData (

    @SerializedName("shipping_address")
    var shippingAddress: ShippingAddress? = null,
    @SerializedName("shipping_method")
    var shippingMethod: ShippingMethod? = null,
    @SerializedName("invoice_details")
    var invoiceDetails: InvoiceDetails? = null
)
:Parcelable




