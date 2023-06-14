package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.extensions.isValidEmail
import com.usend.extensions.isValidPhoneNumber
import com.usend.extensions.nullSafe
import com.usend.repository.UserRepository

class EditProfileViewModel(application: Application, private var repository: UserRepository) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val email by lazy { MediatorLiveData<String>() }
    val firstName by lazy { MediatorLiveData<String>() }
    val lastName by lazy { MediatorLiveData<String>() }
    val addressLine1 by lazy { MediatorLiveData<String>() }
    val addressLine2 by lazy { MediatorLiveData<String>() }
    val country by lazy { MediatorLiveData<String>() }
    val state by lazy { MediatorLiveData<String>() }
    val city by lazy { MediatorLiveData<String>() }
    val zipcode by lazy { MediatorLiveData<String>() }
    val countryCode by lazy { MediatorLiveData<String>() }
    val phoneNumber by lazy { MediatorLiveData<String>() }
    val countryCodeId by lazy { MediatorLiveData<Int>() }

    fun update() {
        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            email.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_please_enter_email)
            !email.value!!.isValidEmail() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_valid_email)
            firstName.value!!.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_first_name_can_not_be_blank)
            lastName.value!!.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_last_name_can_not_be_blank)
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
            /*countryCode.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_select_country_code)*/
            phoneNumber.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_phone_number)
            phoneNumber.value!!.isValidPhoneNumber() -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_valid_phone_number)
            phoneNumber.value!!.length < 7 -> status.value =
                Resource.ValidationError<Int>(R.string.lbl_please_enter_valid_phone_number)
            else -> {
                status.addSource(
                    repository.updateProfileData(
                        authToken = getAuthKey(),
                        email = email.value.nullSafe(),
                        vFirstName = firstName.value.nullSafe(),
                        vLastName = lastName.value.nullSafe(),
                        phone = phoneNumber.value.nullSafe(),
                        countryId = countryCodeId.value.nullSafe(),
                        city = city.value.nullSafe(),
                        state = state.value.nullSafe(),
                        addressLine1 = addressLine1.value.nullSafe(),
                        addressLine2 = addressLine2.value.nullSafe(),
                        zipCode = zipcode.value.nullSafe()
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