package com.usend.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *
 * @param responseCode
 * @param responseMessage
 * @param responseData
 */

@Parcelize
data class ApiException(

        @SerializedName("responseCode")
        var responseCode: Int? = null,
        @SerializedName("responseMessage")
        var responseMessage: String? = null
) : Parcelable




