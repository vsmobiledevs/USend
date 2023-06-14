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
    * @param autoShipmentAddress 
    * @param autoShipmentService 
    * @param cardDetail 
*/

@Parcelize
data class AutoShipmentResponseData (

    @SerializedName("auto_shipment_address")
    var autoShipmentAddress: AddressList? = null,
    @SerializedName("auto_shipment_service")
    var autoShipmentService: GetAutoshipmentServiceListData? = null,
    @SerializedName("card_detail")
    var cardDetail: Card? = null
)
:Parcelable




