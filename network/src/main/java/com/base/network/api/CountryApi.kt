package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.GetCountryResponse
import com.base.network.model.GetUSAStateList


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface CountryApi {
        /**
        * Country list
        * Country list
        * @return Call&lt;GetCountryResponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("country_codes")
    fun getCountryList(
        
        ):Call<GetCountryResponse>

        /**
        * USA State List
        * USA State List
        * @return Call&lt;GetUSAStateList&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("country_codes/usa_state_list")
    fun usaStateList(
        
        ):Call<GetUSAStateList>

}
