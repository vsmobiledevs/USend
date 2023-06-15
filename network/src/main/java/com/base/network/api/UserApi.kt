package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.AddUspsDetailsResponse
import com.base.network.model.GetUspsDetails
import com.base.network.model.SignUpReponse
import com.base.network.model.SuccessModel


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface UserApi {
        /**
        * Add USPS Verification
        * Add USPS Verification
            * @param passportImage Passport Image (required)
            * @param drivingImage Driving Image (required)
            * @param fileName File Name (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;AddUspsDetailsResponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("usps_verifications/add_usps_details")
    fun addUspsVerification(
        @retrofit2.http.Field("passport_image") passportImage:String,
        @retrofit2.http.Field("driving_image") drivingImage:String,
        @retrofit2.http.Field("file_name") fileName:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<AddUspsDetailsResponse>

        /**
        * Change password
        * 
            * @param currentPassword Current password (required)
            * @param password New password (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("users/change_password")
    fun changePassword(
        @retrofit2.http.Field("current_password") currentPassword:String,
        @retrofit2.http.Field("password") password:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SuccessModel>

        /**
        * Change Unit
        * Change Unit
            * @param unitSystem unit system(kg/cm or lbs/inch) (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("users/change_unit")
    fun changeUnit(
        @retrofit2.http.Field("unit_system") unitSystem:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SuccessModel>

        /**
        * Check Email
        * Check Email
            * @param email Email (required)
        * @return Call&lt;SuccessModel&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("users/check_email")
    fun checkEmail(
        @retrofit2.http.Query("email") email:String
        ):Call<SuccessModel>

        /**
        * Edit password
        * 
            * @param resetPasswordToken Reset password token (required)
            * @param password Password (required)
            * @param passwordConfirmation Password confirmation (required)
        * @return Call&lt;SignUpReponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("users/edit_password")
    fun editPassword(
        @retrofit2.http.Field("reset_password_token") resetPasswordToken:String,
        @retrofit2.http.Field("password") password:String,
        @retrofit2.http.Field("password_confirmation") passwordConfirmation:String
        ):Call<SignUpReponse>

        /**
        * Edit User Profile
        * Edit User Profile
            * @param firstName First Name (required)
            * @param lastName Last Name (required)
            * @param email email (required)
            * @param countryCodeId Country ID (required)
            * @param phone Phone No (required)
            * @param city City (required)
            * @param street1 Address line 1 (required)
            * @param street2 Address line 2 (required)
            * @param authorization User Auth Token (optional)
            * @param state State (optional)
            * @param postalCode Zipcode (optional)
        * @return Call&lt;SignUpReponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("users/edit_profile")
    fun editUserProfile(
        @retrofit2.http.Field("first_name") firstName:String,
        @retrofit2.http.Field("last_name") lastName:String,
        @retrofit2.http.Field("email") email:String,
        @retrofit2.http.Field("country_code_id") countryCodeId:Int,
        @retrofit2.http.Field("phone") phone:String,
        @retrofit2.http.Field("city") city:String,
        @retrofit2.http.Field("street_1") street1:String,
        @retrofit2.http.Field("street_2") street2:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("state") state:String?=null,
        @retrofit2.http.Field("postal_code") postalCode:String?=null
        ):Call<SignUpReponse>

        /**
        * Forgot password
        * 
            * @param email Email (required)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("users/password")
    fun forgotPassword(
        @retrofit2.http.Field("email") email:String
        ):Call<SuccessModel>

        /**
        * Get User Profile
        * Get User Profile
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SignUpReponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("users/get_profile")
    fun getUserProfile(
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SignUpReponse>

        /**
        * Get USPS Details
        * Get USPS Details
            * @param authorization User Auth Token (optional)
        * @return Call&lt;GetUspsDetails&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("usps_verifications/get_usps_details")
    fun getUspsDetails(
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<GetUspsDetails>

        /**
        * Log out
        * 
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SuccessModel&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @DELETE("users/logout")
    fun logOut(
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SuccessModel>

    @retrofit2.http.FormUrlEncoded
    @POST("users/delete_user")
    fun deleteUser(
        @retrofit2.http.Field("user_id") useId:Int,
        @retrofit2.http.Header("Authorization") authorization:String?=null
    ):Call<SuccessModel>

        /**
        * To resend OTP
        * 
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SignUpReponse&gt;
        */
    @PUT("users/resend_otp")
    fun resendOTP(
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SignUpReponse>

        /**
        * Sign In
        * 
            * @param email Email (required)
            * @param password Password (required)
            * @param deviceToken Device token (optional)
            * @param deviceType Device type: android/ios (optional)
        * @return Call&lt;SignUpReponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("users/sign_in")
    fun signIn(
        @retrofit2.http.Field("email") email:String,
        @retrofit2.http.Field("password") password:String,
        @retrofit2.http.Field("device_token") deviceToken:String?=null,
        @retrofit2.http.Field("device_type") deviceType:String?=null
        ):Call<SignUpReponse>

        /**
        * Sign up user
        * 
            * @param firstName First Name (required)
            * @param lastName Last Name (required)
            * @param email Email (required)
            * @param password Password (required)
            * @param countryCodeId Country Id (required)
            * @param phone Phone No (required)
            * @param city City (required)
            * @param street1 Address line 1 (required)
            * @param street2 Address line 2 (required)
            * @param postalCode Zipcode (required)
            * @param state State (optional)
            * @param deviceToken Device token (optional)
            * @param deviceType Device type: android/ios (optional)
        * @return Call&lt;SignUpReponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("users")
    fun signUp(
        @retrofit2.http.Field("first_name") firstName:String,
        @retrofit2.http.Field("last_name") lastName:String,
        @retrofit2.http.Field("email") email:String,
        @retrofit2.http.Field("password") password:String,
        @retrofit2.http.Field("country_code_id") countryCodeId:Int,
        @retrofit2.http.Field("phone") phone:String,
        @retrofit2.http.Field("city") city:String,
        @retrofit2.http.Field("street_1") street1:String,
        @retrofit2.http.Field("street_2") street2:String,
        @retrofit2.http.Field("postal_code") postalCode:String,
        @retrofit2.http.Field("state") state:String?=null,
        @retrofit2.http.Field("device_token") deviceToken:String?=null,
        @retrofit2.http.Field("device_type") deviceType:String?=null
        ):Call<SignUpReponse>

        /**
        * UpdateMobileNumber
        * 
            * @param phone Phone No (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("users/update_mobile_number")
    fun updateMobileNumber(
        @retrofit2.http.Field("phone") phone:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SignUpReponse>

        /**
        * UpdateNotificationPreference
        * 
            * @param notificationPreference Notification Preference (required)
            * @param authorization User Auth Token (optional)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("users/update_notification_preference")
    fun updateNotificationPreference(
        @retrofit2.http.Field("notification_preference") notificationPreference:Boolean,
        @retrofit2.http.Header("Authorization") authorization:String?=null
        ):Call<SuccessModel>

        /**
        * To verify OTP
        * 
            * @param otp 4 digit OTP (required)
            * @param authorization User Auth Token (optional)
            * @param deviceToken Device token - Required when verifying OTP after sign up (optional)
            * @param deviceType Device type: android/ios - Required when verifying OTP after sign up (optional)
        * @return Call&lt;SignUpReponse&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @POST("users/verify_otp")
    fun verifyOTP(
        @retrofit2.http.Field("otp") otp:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("device_token") deviceToken:String?=null,
        @retrofit2.http.Field("device_type") deviceType:String?=null
        ):Call<SignUpReponse>

}
