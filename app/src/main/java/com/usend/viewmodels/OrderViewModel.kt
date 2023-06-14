package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.repository.UserRepository

class OrderViewModel(application: Application, private var repository: UserRepository) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    fun getOrderDetail(id: Int) {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.getOrderDetails(
                        authToken = getAuthKey(),
                        order_id = id
                    )
                ) {
                    if (it is Resource.Success<*>) {
                        //can save for further use

                    }
                    status.value = it
                }
            }
        }
    }

    fun getOrdersList() {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.getOrderLists(authToken = getAuthKey())
                ) {
                    if (it is Resource.Success<*>) {
                        //can save for further use
                    }
                    status.value = it
                }
            }
        }
    }
}