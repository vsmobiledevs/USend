package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.extensions.isValidEmail
import com.usend.extensions.nullSafe
import com.usend.repository.AuthenticationRepository
import com.usend.utils.PREF_FCM_TOKEN

class SignInViewModel(application: Application, private var repository: AuthenticationRepository) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val email by lazy { MediatorLiveData<String>() }
    val password by lazy { MediatorLiveData<String>() }

    fun signIn() {
        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            email.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_please_enter_email)
            !email.value.toString().isValidEmail() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_valid_email)
            password.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<String>(R.string.msg_please_enter_password)
            else -> {
                status.addSource(
                    repository.signIn(
                        email = email.value.nullSafe(),
                        password = password.value.nullSafe(),
                        token = prefs.getString(PREF_FCM_TOKEN, "").nullSafe()
                    )
                ) {

                    if (it is Resource.Success<*>) {

                    }
                    status.value = it
                }
            }
        }
    }
}