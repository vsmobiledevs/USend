package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.extensions.isValidPhoneNumber
import com.usend.extensions.nullSafe
import com.usend.models.UserModel
import com.usend.repository.AuthenticationRepository
import com.usend.utils.PREF_FCM_TOKEN

class BillingAddressViewModel(
    application: Application,
    private var repository: AuthenticationRepository
) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val addressLine1 by lazy { MediatorLiveData<String>() }
    val addressLine2 by lazy { MediatorLiveData<String>() }
    val country by lazy { MediatorLiveData<String>() }
    val zipcode by lazy { MediatorLiveData<String>() }
    val city by lazy { MediatorLiveData<String>() }
    val state by lazy { MediatorLiveData<String>() }
    val countryCode by lazy { MediatorLiveData<String>() }
    val phoneNumber by lazy { MediatorLiveData<String>() }
    val countryCodeId by lazy { MediatorLiveData<Int>() }

    var user: UserModel = UserModel()


    fun next() {
        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            addressLine1.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_enter_address_line1)
            /*addressLine2.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_enter_address_line2)*/
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
            phoneNumber.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_phone_number)
            phoneNumber.value!!.isValidPhoneNumber() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_valid_phone_number)
            phoneNumber.value!!.length < 7 -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_valid_phone_number)

            else -> {
                status.addSource(
                    repository.signUp(
                        user.firstName.nullSafe(),
                        user.lastName.nullSafe(),
                        user.email.nullSafe(),
                        user.password.nullSafe(),
                        country.value.nullSafe(),
                        countryCodeId.value.nullSafe(),
                        phoneNumber.value.nullSafe(),
                        state.value.nullSafe(),
                        city.value.nullSafe(),
                        addressLine1.value.nullSafe(),
                        addressLine2.value.nullSafe(),
                        zipcode.value.nullSafe(),
                        prefs.getString(PREF_FCM_TOKEN, "").nullSafe(),
                        "android"
                    )
                ) {

                    if (it is Resource.Success<*>) {

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