package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.CmsPageResponse
import com.base.network.model.GetReasonResponse
import com.base.network.model.SuccessModel


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface CmsPagesApi {
        /**
        * Cms Pages
        * Cms Pages
            * @param type Type (required)
        * @return Call&lt;CmsPageResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("cms_pages/get_page")
    fun cmsPages(
        @retrofit2.http.Query("type") type:String
        ):Call<CmsPageResponse>

        /**
        * ContactUsInfo
        * Contact Us Info
            * @param name Name (required)
            * @param email Email (required)
            * @param message Message (required)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("cms_pages/contact_us_info")
    fun contactUsInfo(
        @retrofit2.http.Field("name") name:String,
        @retrofit2.http.Field("email") email:String,
        @retrofit2.http.Field("message") message:String
        ):Call<SuccessModel>

        /**
        * Get Reason
        * Get Reason
        * @return Call&lt;GetReasonResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("cms_pages/get_reason")
    fun getReasonList(
        
        ):Call<GetReasonResponse>

}
