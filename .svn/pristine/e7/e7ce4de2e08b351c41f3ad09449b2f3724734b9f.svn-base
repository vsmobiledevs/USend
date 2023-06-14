package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.repository.UserRepository

class ConciergeViewModel(application: Application, private var repository: UserRepository) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    fun getConciergeList() {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.getConciergeList(authToken = getAuthKey())
                ) {
                    if (it is Resource.Success<*>) {
                        //can save for further use
                    }
                    status.value = it
                }
            }
        }
    }

    fun payConciergeAmount(card_id : String, amount_to_pay : Float, id : Int) {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.payConcierge(card_id, amount_to_pay, id, authToken = getAuthKey())
                ) {
                    if (it is Resource.ConciergePayment<*>) {
                        //can save for further use
                    }
                    status.value = it
                }
            }
        }
    }

    fun subscriptionRetrive( subscriptionID : String) {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.retriveSubscriptionSquareUp(subscriptionID)
                ) {
                    if (it is Resource.Success<*>) {
                        //can save for further use
                    }
                    status.value = it
                }
            }
        }
    }
    fun subscriptionCancel( subscriptionID : String) {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.cancelSubscriptionSquareUp(subscriptionID)
                ) {
                    if (it is Resource.SuccessCancel<*>) {
                        //can save for further use
                    }
                    status.value = it
                }
            }
        }
    }

}