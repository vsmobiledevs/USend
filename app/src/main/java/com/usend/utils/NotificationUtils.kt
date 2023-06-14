/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 https://www.spaceotechnologies.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.usend.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.provider.Settings
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import com.usend.R
import com.usend.views.MainActivity
import java.util.*
import kotlin.math.abs

/**
 * Created by Bhavesh Jabuvani on 2/5/18.
 */
object NotificationUtils {

    private val NOTIFICATION_CHANNEL_ID = "com.usend.notification"
    private val rnd = Random()
    /**
     * Generate local notification
     * */
    fun generateNotification(context: Context, title: String, message: String, intent: Intent?) {

        /* val intent = Intent(context, SplashActivity::class.java)
         intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP*/

        //intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        //intent?.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent?.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addNextIntentWithParentStack(Intent(context,MainActivity::class.java))
        stackBuilder.addNextIntent(intent!!)

        val pendingIntent1 =
            stackBuilder.getPendingIntent(abs(rnd.nextInt()), PendingIntent.FLAG_ONE_SHOT)

        val pendingIntent = PendingIntent.getActivity(
            context,
            abs(rnd.nextInt()),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT/*Flag indicating that this PendingIntent can be used only once.*/
        )

        val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_push_notification)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        R.mipmap.app_icon_round
                    )
                )
                .setContentTitle(title)
                .setStyle(NotificationCompat.BigTextStyle().bigText(message))
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI) // Set default notification sound
                .setContentIntent(pendingIntent1)

        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the channel object with the unique ID NOTIFICATION_CHANNEL_ID.
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                context.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )

            // Configure the channel's initial settings
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.vibrationPattern = longArrayOf(
                100,
                200,
                300,
                400,
                500,
                400,
                500,
                200,
                500
            )

            // Submit the notification channel object to the notification manager
            notificationManager.createNotificationChannel(notificationChannel)
        } else {

        }
        //random function
        notificationManager.notify(abs(rnd.nextInt()), notificationBuilder.build())
    }


}