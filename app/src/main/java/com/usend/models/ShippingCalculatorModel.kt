package com.usend.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShippingCalculatorModel (var countryCodeId : Int,
                                    var city : String,
                                    var weight : String,
                                    var weight_unit : String,
                                    var height : String,
                                    var length : String,
                                    var width : String,
                                    var dimensionUnit : String,
                                    var zipcode : String) : Parcelable