package com.usend.viewmodels
import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.repository.AuthenticationRepository
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.extensions.isValidEmail

class ForgotPassViewModel(application: Application, private var repository: AuthenticationRepository):
    BaseViewModel(application){

    val mContext: Context = application.applicationContext

    val status : MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val email : MediatorLiveData<String> by lazy {
        MediatorLiveData<String>()
    }

    fun onSubmit() {
        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            email.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_please_enter_email)
            !email.value!!.isValidEmail() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_valid_email)
            else -> {
                status.addSource(repository.forgotPassword(email = email.value!!)) {
                    if (it is Resource.Success<*>) {
                        //can save for further use

                    }
                    status.value = it
                }
            }
        }
    }
}