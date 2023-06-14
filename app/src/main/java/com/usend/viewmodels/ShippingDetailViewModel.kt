package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.repository.UserRepository

class ShippingDetailViewModel(application: Application, private var repository: UserRepository) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    fun getShippingDetails(
        address_id: Int,
        package_ids: String,
        serviceName: String,
        ship_cost: Double,
        serviceCode: String,
        carrierCode: String
    ) {
        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.getShippingDetails(
                        getAuthKey(),
                        address_id = address_id,
                        package_Ids = package_ids,
                        service_name = serviceName,
                        cost = ship_cost,
                        serviceCode = serviceCode,
                        carrierCode = carrierCode
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

    fun getAdditionalServices() {
        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.getAdditionalServices()
                ) {

                    if (it is Resource.AdditionalServicesSucess<*>) {
                        //can save for further use

                    }

                    status.value = it
                }
            }

        }
    }
}