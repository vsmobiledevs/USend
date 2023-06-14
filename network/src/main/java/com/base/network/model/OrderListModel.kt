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
    * @param orderDetails 
    * @param paymentDetails 
    * @param packageDetails 
    * @param deliveryAddress 
    * @param additionalCharge 
    * @param invoiceDetails 
*/

@Parcelize
data class OrderListModel (

    @SerializedName("order_details")
    var orderDetails: OrderDetails? = null,
    @SerializedName("payment_details")
    var paymentDetails: OrderPaymentDetails? = null,
    @SerializedName("package_details")
    var packageDetails: List<OrderPackageDetails>? = null,
    @SerializedName("delivery_address")
    var deliveryAddress: ShippingAddress? = null,
    @SerializedName("additional_charge")
    var additionalCharge: List<ServiceChargeList>? = null,
    @SerializedName("invoice_details")
    var invoiceDetails: InvoiceDetails? = null
)
:Parcelable




