package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.usend.R
import com.usend.base.BaseViewModel
import com.usend.comman.Resource
import com.usend.extensions.callApi
import com.usend.extensions.checkInternetConnected
import com.usend.repository.ApiServices
import com.usend.repository.UserRepository

class MailboxViewModel(var myApplication: Application, private var repository: UserRepository) :
    BaseViewModel(myApplication) {

    val mContext: Context = myApplication.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val isSelectMode by lazy { MutableLiveData<Boolean>(false) }

    fun getPackageListApi() {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(repository.getPackageList(getAuthKey())) {
                    if (it is Resource.Success<*>) {
                        //can save for further use

                    }
                    status.value = it
                }
            }
        }
    }

    fun packageSendToTrash(id: String) {

        myApplication.callApi(
            ApiServices.getCouponApiService().packageSendToTrash(
                id = id,
                authorization = getAuthKey()
            ), liveData = status,
            showLoading = true
        )
    }
}