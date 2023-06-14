package com.base.network.api

import retrofit2.Call
import retrofit2.http.*


import com.base.network.model.NotificationsListReponse
import com.base.network.model.SuccessModel


//Amit Changed : 23 Oct 2018
//Do not change, If any issue ask Amit

interface NotificationApi {
        /**
        * ListNotification
        * 
            * @param authorization User Auth Token (optional)
            * @param page Page number (optional)
        * @return Call&lt;NotificationsListReponse&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @GET("notifications")
    fun listNotification(
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Query("page") page:Int?=null
        ):Call<NotificationsListReponse>

        /**
        * RemoveNotification
        * 
            * @param type type: clear_all- remove all notification, single- remove single notification (required)
            * @param authorization User Auth Token (optional)
            * @param notificationId Notification ID (optional)
        * @return Call&lt;SuccessModel&gt;
        */
                    @Headers(
                    "Content-Type:application/x-www-form-urlencoded"
                    )
    @DELETE("notifications/remove_notification")
    fun notificationsRemoveNotificationDelete(
        @retrofit2.http.Query("type") type:String,
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Query("notification_id") notificationId:Int?=null
        ):Call<SuccessModel>

        /**
        * ReadNotification
        * 
            * @param authorization User Auth Token (optional)
            * @param notificationId Notification ID (optional)
            * @param type type: read_all- read_all all notification of user (optional)
        * @return Call&lt;SuccessModel&gt;
        */
    @retrofit2.http.FormUrlEncoded
    @PUT("notifications/mark_as_read")
    fun readNotification(
        @retrofit2.http.Header("Authorization") authorization:String?=null,
        @retrofit2.http.Field("notification_id") notificationId:Int?=null,
        @retrofit2.http.Query("type") type:String?=null
        ):Call<SuccessModel>

}
