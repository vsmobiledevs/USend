package com.usend.viewmodels
import android.app.Application
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.repository.AuthenticationRepository
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.extensions.nullSafe
import com.usend.utils.PREF_FCM_TOKEN

class OtpVerificationViewModel(application: Application, repository: AuthenticationRepository):
    BaseViewModel(application){

    private val mContext = application.applicationContext

    val status : MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }
    val otp by lazy { MediatorLiveData<String>() }

    val isTimer by lazy { MediatorLiveData<Boolean>() }

    private var repository : AuthenticationRepository = repository

    fun verify() {
        when {
            !mContext.checkInternetConnected() -> status.value = Resource.NoInternetError<Int>(R.string.default_internet_message)
            otp.value.isNullOrEmpty() -> status.value = Resource.ValidationError<Int>(R.string.msg_enter_otp)
            otp.value?.length != 4 -> status.value = Resource.ValidationError<Int>(R.string.msg_enter_valid_otp)
            else -> {
                status.addSource(repository.verifyOtp(otp.value!!,getAuthKey(), prefs.getString(
                    PREF_FCM_TOKEN, "").nullSafe(),
                    "android")) {
                    if (it is Resource.VerifyOTPSuccess<*>) {
                        //can save for further use
                    }
                    status.value = it
                }
            }
        }
    }

    fun resend() {
        when {
            isTimer.value!! -> {
                status.value = Resource.ValidationError<Int>(R.string.msg_please_wait)
            }
            !mContext.checkInternetConnected() -> status.value = Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(repository.resendOtp(getAuthKey())) {

                    if (it is Resource.Success<*>) {
                        //can save for further use

                    }

                    status.value = it
                }
            }
        }
    }

    fun updateMobile(countryCode : String, phone : String) {
        when {
            isTimer.value!! -> {
                status.value = Resource.ValidationError<Int>(R.string.msg_please_wait)
            }
            !mContext.checkInternetConnected() -> status.value = Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(repository.updateMobile(getAuthKey(), phone, countryCode)) {

                    if (it is Resource.Success<*>) {
                        //can save for further use

                    }

                    status.value = it
                }
            }
        }
    }
}