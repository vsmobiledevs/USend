/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 https://www.spaceotechnologies.com
 *
 * Permissions is hereby granted, free of charge, to any person obtaining a copy of this software and associated
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

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.usend.R


/**
 * Created by sotsys-264 on 18th Sept 2019.
 */

class DownloadUtils(var mContext: Context) {

    lateinit var downloadManager: DownloadManager

    fun registerDownloadBroadcast() {
        val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        mContext.registerReceiver(downloadReceiver, filter)
    }

    fun unRegisterDownloadBroadcast() {
        mContext.unregisterReceiver(downloadReceiver)
    }

    fun downloadData(uri: Uri, title: String, Description: String, subPath: String): Long {

        val downloadReference: Long
        downloadManager = mContext.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(uri)

        request.setTitle(title)

        request.setDescription(Description)

        request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, subPath)
        downloadReference = downloadManager.enqueue(request)

        return downloadReference
    }

    private val downloadReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            Toast.makeText(context, "Image Download Complete : $referenceId", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun checkDownloadStatus(downloadID: Long) {
        val imageDownloadQuery = DownloadManager.Query()

        imageDownloadQuery.setFilterById(downloadID)

        val cursor = downloadManager.query(imageDownloadQuery)

        if (cursor.moveToFirst()) {
            downloadStatus(cursor, downloadID)
        }
    }

    fun downloadStatus(cursor: Cursor, downloadID: Long) {
        val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
        val status = cursor.getInt(columnIndex)

        val columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON)
        val reason = cursor.getInt(columnReason)

        var statusText = ""
        var reasonText = ""

        when (status) {
            DownloadManager.STATUS_FAILED -> {
                statusText = mContext.getString(R.string.txt_status_failed)
                when (reason) {
                    DownloadManager.ERROR_CANNOT_RESUME -> reasonText =
                        mContext.getString(R.string.txt_error_cannot_resume)
                    DownloadManager.ERROR_DEVICE_NOT_FOUND -> reasonText =
                        mContext.getString(R.string.txt_device_not_found)
                    DownloadManager.ERROR_FILE_ALREADY_EXISTS -> reasonText =
                        mContext.getString(R.string.txt_error_file_exists)
                    DownloadManager.ERROR_FILE_ERROR -> reasonText =
                        mContext.getString(R.string.txt_error_file_error)
                    DownloadManager.ERROR_HTTP_DATA_ERROR -> reasonText =
                        mContext.getString(R.string.txt_error_http_data_error)
                    DownloadManager.ERROR_INSUFFICIENT_SPACE -> reasonText =
                        mContext.getString(R.string.txt_error_no_space)
                    DownloadManager.ERROR_TOO_MANY_REDIRECTS -> reasonText =
                        mContext.getString(R.string.txt_error_too_many_redirect)
                    DownloadManager.ERROR_UNHANDLED_HTTP_CODE -> reasonText =
                        mContext.getString(R.string.txt_error_unhandled_http_code)
                    DownloadManager.ERROR_UNKNOWN -> reasonText =
                        mContext.getString(R.string.txt_error_unknown)
                }
            }

            DownloadManager.STATUS_PAUSED -> {
                statusText = mContext.getString(R.string.txt_status_paused)
                when (reason) {
                    DownloadManager.PAUSED_QUEUED_FOR_WIFI -> reasonText =
                        mContext.getString(R.string.txt_queued_for_wifi)
                    DownloadManager.PAUSED_UNKNOWN -> reasonText =
                        mContext.getString(R.string.txt_paused_unknown)
                    DownloadManager.PAUSED_WAITING_FOR_NETWORK -> reasonText =
                        mContext.getString(R.string.txt_pause_for_network)
                    DownloadManager.PAUSED_WAITING_TO_RETRY -> reasonText =
                        mContext.getString(R.string.txt_wait_for_retry)
                }
            }

            DownloadManager.STATUS_PENDING -> {
                statusText = mContext.getString(R.string.txt_status_pending)
            }
            DownloadManager.STATUS_RUNNING -> {
                statusText = mContext.getString(R.string.txt_status_running)
            }
            DownloadManager.STATUS_SUCCESSFUL -> {
                statusText = mContext.getString(R.string.txt_status_successful)
                reasonText = "Filename:\n" + ""
            }
        }

        Toast.makeText(
            mContext,
            "Image Download Status:\n$statusText\n$reasonText",
            Toast.LENGTH_LONG
        ).show()

    }


}