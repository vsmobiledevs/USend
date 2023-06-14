package com.usend.viewmodels
import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.repository.UserRepository

class ContactUsViewModel(application: Application, private var repository: UserRepository):
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val reasonId by lazy { MutableLiveData<Int>()}
    val reason by lazy { MutableLiveData<String>()}
    val message by lazy { MutableLiveData<String>()}

    fun submit(email : String, name : String)
    {
        when {
            !mContext.checkInternetConnected() -> status.value = Resource.NoInternetError<Int>(R.string.default_internet_message)
            //reason.value.isNullOrEmpty() -> status.value = Resource.ValidationError<Int>(R.string.msg_please_select_reason)
            //reason.value == "Select" -> status.value = Resource.ValidationError<Int>(R.string.msg_please_select_reason)
            message.value.isNullOrEmpty() -> status.value = Resource.ValidationError<Int>(R.string.msg_please_write_message)
            else -> {

                status.addSource(repository.contactUsApi(email = email, name = name, msg = message.value!!)) {

                    if (it is Resource.Success<*>) {
                        //can save for further use

                    }

                    status.value = it
                }
            }
        }
    }

    fun getContactUsReasons()
    {
        when {
            !mContext.checkInternetConnected() -> status.value = Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {

                status.addSource(repository.getReasonList()) {

                    if (it is Resource.GetReasonsSuccess<*>) {
                        //can save for further use

                    }

                    status.value = it
                }
            }
        }
    }

}