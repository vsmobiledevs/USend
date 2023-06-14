package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.extensions.isValidPhoneNumber
import com.usend.extensions.nullSafe
import com.usend.repository.UserRepository

class AddNewAddressViewModel(
    application: Application,
    private var repository: UserRepository
) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val cbSameAsBilling by lazy { MutableLiveData<Boolean>(false) }
    val label by lazy { MediatorLiveData<String>() }
    val receiverName by lazy { MediatorLiveData<String>() }
    val addressLine1 by lazy { MediatorLiveData<String>() }
    val addressLine2 by lazy { MediatorLiveData<String>() }
    val country by lazy { MediatorLiveData<String>() }
    val zipcode by lazy { MediatorLiveData<String>() }
    val city by lazy { MediatorLiveData<String>() }
    val state by lazy { MediatorLiveData<String>() }
    val countryCode by lazy { MediatorLiveData<String>() }
    val phoneNumber by lazy { MediatorLiveData<String>() }
    val countryCodeId by lazy { MediatorLiveData<Int>() }

    fun add() {
        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            label.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_plz_enter_label_address)
            receiverName.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_plz_enter_receiver_name)
            addressLine1.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_enter_address_line1)
            country.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_country)
            zipcode.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_valid_zipcode)
            city.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_city)
            countryCodeId.value.nullSafe() == 198 && zipcode.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_plz_enter_zipcode)
            countryCodeId.value.nullSafe() == 198 && state.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_state)
            /*addressLine2.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_enter_address_line2)*/
            phoneNumber.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_phone_number)
            phoneNumber.value!!.isValidPhoneNumber() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_valid_phone_number)
            phoneNumber.value!!.length < 7 -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_valid_phone_number)

            else -> {
                status.addSource(
                    repository.addShippingAddress(
                        label = label.value.nullSafe(),
                        receiverName = receiverName.value.nullSafe(),
                        state = state.value.nullSafe(),
                        city = city.value.nullSafe(),
                        addressLine1 = addressLine1.value.nullSafe(),
                        addressLine2 = addressLine2.value.nullSafe(),
                        countryCodeId = countryCodeId.value.nullSafe(),
                        phone = phoneNumber.value.nullSafe(),
                        zipcode = zipcode.value.nullSafe(),
                        authToken = getAuthKey()
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

    fun update(id : Int) {
        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            label.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_plz_enter_label_address)
            receiverName.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_plz_enter_receiver_name)
            addressLine1.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_enter_address_line1)
            country.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_country)
            zipcode.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_valid_zipcode)
            /*state.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_state)*/
            city.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_city)
            countryCodeId.value.nullSafe() == 198 && zipcode.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_plz_enter_zipcode)
            countryCodeId.value.nullSafe() == 198 && state.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_state)
            /*addressLine2.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_enter_address_line2)*/
            countryCode.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_country_code)
            phoneNumber.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_phone_number)
            phoneNumber.value!!.isValidPhoneNumber() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_valid_phone_number)

            else -> {
                status.addSource(
                    repository.updateShippingAddress(
                        label = label.value.nullSafe(),
                        receiverName = receiverName.value.nullSafe(),
                        state = state.value.nullSafe(),
                        city = city.value.nullSafe(),
                        addressLine1 = addressLine1.value.nullSafe(),
                        addressLine2 = addressLine2.value.nullSafe(),
                        countryCodeId = countryCodeId.value.nullSafe(),
                        phone = phoneNumber.value.nullSafe(),
                        zipcode = zipcode.value.nullSafe(),
                        authToken = getAuthKey(),
                        id = id
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

    fun delete(id : Int) {
        when {
            !mContext.checkInternetConnected() -> status.value = Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(repository.deleteShippingAddress(getAuthKey(), id = id)) {

                    if (it is Resource.Success<*>) {
                        //can save for further use

                    }

                    status.value = it
                }
            }
        }
    }


    fun getCountryList() {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.getCountryList()
                ) {

                    if (it is Resource.GetCountryListSucess<*>) {

                    }
                    status.value = it
                }
            }
        }
    }

    fun getStateList() {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            else -> {
                status.addSource(
                    repository.getStateList()
                ) {

                    if (it is Resource.GetStateListSucess<*>) {

                    }
                    status.value = it
                }
            }
        }
    }

}