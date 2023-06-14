package com.usend.extensions

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.usend.R
import com.usend.utils.CommonUtils





fun Context.checkInternetConnected(): Boolean {
    var isConnected = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                isConnected = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        cm?.run {
            @Suppress("DEPRECATION")
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isConnected = true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    isConnected = true
                }
            }
        }
    }
    return isConnected
}


fun Context.isInternetConnected(isShowDialog: Boolean = false): Boolean {
    var isConnected = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                isConnected = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
    } else {
        cm?.run {
            @Suppress("DEPRECATION")
            cm.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isConnected = true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    isConnected = true
                }
            }
        }
    }
    if (!isConnected && isShowDialog) {
        CommonUtils.showOkDialog(
            this, getString(R.string.app_name),
            getString(R.string.default_internet_message)
        )

    }
    return isConnected
}





/**
 *  Show Toast
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).apply { show() }
}



fun Activity.hideSoftKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}



fun Activity.color(resource: Int) = ContextCompat.getColor(this, resource)

fun Context.layoutInflater() = (this as Activity).layoutInflater








