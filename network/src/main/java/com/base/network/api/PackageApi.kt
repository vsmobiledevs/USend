package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.ListPackageResponse
import com.base.network.model.PackageDetailResponse
import com.base.network.model.SuccessModel


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface PackageApi {
        /**
        * Package Details
        * Package Details
            * @param id ID of package (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;PackageDetailResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("packages/package_details")
    fun packageDetails(
        @retrofit2.http.Query("id") id:Int,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<PackageDetailResponse>

        /**
        * Package List
        * Package List
            * @param authorization User Auth Token (optional)
        * @return Call&lt;ListPackageResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("packages")
    fun packageList(
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<ListPackageResponse>

        /**
        * Package Removed from Inbox
        * Package Removed from Inbox
            * @param id Package ids - Pass comma seperated ids (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("packages/package_send_to_trash")
    fun packageSendToTrash(
        @retrofit2.http.Field("id") id:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SuccessModel>

        /**
        * Split Package
        * Split Package
            * @param id ID of package (required)
            * @param splitCount Number of split of package (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("packages/split_package")
    fun splitPackage(
        @retrofit2.http.Field("id") id:Int,
        @retrofit2.http.Field("split_count") splitCount:Int,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SuccessModel>

}
