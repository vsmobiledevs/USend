package com.usend.viewmodels
import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.base.network.model.CarrierList
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.repository.UserRepository

class ShippingCostViewModel(application: Application, private var repository: UserRepository):
    BaseViewModel(application){

    val mContext: Context = application.applicationContext
    var response = MutableLiveData<Any>()

    val status : MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val selectedPriceFilter : MutableLiveData<String> by lazy {
        MutableLiveData<String>(mContext.resources.getString(R.string.lbl_high_to_low))
    }

    fun getShippingMethods(countryCodeId : Int, city : String,weight : Double, height : Double, length : Double,weightSelected : String, dimensonSelected : String,
                           zipCode : String, width : Double, carrierCode : String, priceFilter:String,carrierList:List<CarrierList>) {
        when {
            !mContext.checkInternetConnected() -> status.value = Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.getShippingCalculator(
                        countryCodeId = countryCodeId,
                        city = city,
                        weight = weight,
                        weight_unit = weightSelected,
                        height = height,
                        length = length,
                        width = width,
                        zipCode = zipCode,
                        dimensionUnit = dimensonSelected,
                        price_filter=priceFilter,
                        carrierList = carrierList,
                        observer = status,
                    )
                ) {

                    if (it is Resource.Success<*>) {

                    }
                    //status.value = response
                }
            }
        }
    }
}