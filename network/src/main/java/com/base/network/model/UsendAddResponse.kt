package com.base.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsendAddResponse(
    @SerializedName("responseCode")
    var responseCode: Int? = null,
    @SerializedName("responseMessage")
    var responseMessage: String? = null,


)
    : Parcelable