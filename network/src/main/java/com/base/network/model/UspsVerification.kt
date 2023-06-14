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
    * @param id 
    * @param userId 
    * @param passportImage 
    * @param drivingImage 
    * @param fileName 
    * @param status 
*/

@Parcelize
data class UspsVerification (

    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("user_id")
    var userId: Int? = null,
    @SerializedName("passport_image")
    var passportImage: String? = null,
    @SerializedName("driving_image")
    var drivingImage: String? = null,
    @SerializedName("file_name")
    var fileName: String? = null,
    @SerializedName("status")
    var status: String? = null
)
:Parcelable



