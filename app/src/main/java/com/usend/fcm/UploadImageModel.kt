package com.usend.fcm

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
data class UploadImageModel (var mTitle : String = "",
                             var mFile : File? = null,
                             var mUploadName : String = "",
                             var preUploadedImageName : String = "",
                             var isUploaded : Boolean = false): Parcelable
