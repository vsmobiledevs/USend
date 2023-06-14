package com.usend.extensions

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.usend.BuildConfig
import com.usend.R
import com.usend.comman.Resource
import com.usend.models.ApiException
import com.usend.utils.JLog
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *  Calling Network call with Retrofit Api
 */
fun <T> Application.callApi(
    call: Call<T>,
    liveData: MutableLiveData<Any>,
    showLoading: Boolean = true,
    showError: Boolean = true,
    model: Any? = null
) {

    if (!isInternetConnected(false)) {
        if (showError) {
            handleInternet()
        } else {
            var message = "No internet connection"
            try {
                message = getString(R.string.default_internet_message)
            } catch (e: Exception) {
                JLog.e("handleInternet", "Internet issue")
            }
            liveData.postValue(Resource.Error<String>(message))
        }
        return
    }

    if (showLoading) {
        liveData.postValue(Resource.Loading<Boolean>(true))
        //showProgressDialog(this)
    }

    call.enqueue {
        onResponse = {
            liveData.postValue(Resource.Loading<Boolean>(false))
            if (it.body() != null) {
                val gson = Gson()
                val strData = gson.toJson(it.body())
                val json = gson.fromJson(strData ?: "", ApiException::class.java)
                checkForStatus(json)
                /*if (json.responseCode == UNAUTHORISED)
                    liveData.postValue(Resource.UnAuthorisedRequest<String>(json.responseMessage.toString()))
                else*/
                    liveData.postValue(Resource.SuccessWithData(it.body(), model))
            }
        }
        onFailure = { _, t ->
            liveData.postValue(Resource.Loading<Boolean>(false))
            if (showError) {
                handleFailure(t)
            } else {
                liveData.postValue(Resource.Error<String>(t.toString()))
            }
        }
    }
}

private fun checkForStatus(json: ApiException) {
    try {
        ("ApiCallExtension" + "Code " + json.responseCode).info()
        ("ApiCallExtension" + "Message " + json.responseMessage).info()
    } catch (e: Exception) {
        ("ApiCallExtension" + e.stackTrace.toString()).error()
    }
}

fun <T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}


class CallBackKt<T> : Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((body: ResponseBody?, t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(null, t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onResponse?.invoke(response)
        } else {
            onFailure?.invoke(response.errorBody(), null)
        }
    }

}

/**
 *  Handle Network failure here
 */
fun Application.handleFailure(t: Throwable?) {
    if (BuildConfig.DEBUG) {
        t?.printStackTrace()
    }
    showAppToast(this, "Something went wrong")
}


fun Application.handleInternet() {
    try {
        val message = getString(R.string.default_internet_message)
        showAppToast(this, message)
    } catch (e: Exception) {
        JLog.e("handleInternet", "Internet issue")
    }
}
