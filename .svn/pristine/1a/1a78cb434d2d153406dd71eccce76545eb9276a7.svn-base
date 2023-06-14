package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.models.UserModel
import com.usend.repository.AuthenticationRepository
import com.usend.utils.DateTimeUtil

class PaymentInfoViewModel(
    application: Application,
    private var repository: AuthenticationRepository
) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val cardNumber by lazy { MediatorLiveData<String>() }
    val cardName by lazy { MediatorLiveData<String>() }
    val month by lazy { MediatorLiveData<String>() }
    val year by lazy { MediatorLiveData<String>() }
    val cvv by lazy { MediatorLiveData<String>() }
    var user: UserModel = UserModel()

    fun submit() {

        when {
            !mContext.checkInternetConnected() -> status.value =
                Resource.NoInternetError<Int>(R.string.default_internet_message)
            cardNumber.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_card_number_can_be_blank)
            cardNumber.value!!.length < 15 -> status.value =
                Resource.ValidationError<Int>(R.string.msg_please_enter_valid_card_number)
            cardName.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_card_name_can_not_be_blank)
            month.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_select_valid_month)
            year.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_select_valid_year)
            month.value!!.toInt() > 12 -> status.value =
                Resource.ValidationError<Int>(R.string.msg_select_valid_month)
            year.value!!.toInt() < DateTimeUtil.getCurrentYear().toInt() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_enter_valid_month_year)
            month.value!!.toInt() < DateTimeUtil.getCurrentMonth()
                .toInt() && year.value!!.toInt() <= DateTimeUtil.getCurrentYear()
                .toInt() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_enter_valid_month_year)
            cvv.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_cvv_can_not_be_blank)
            cvv.value!!.length < 3 -> status.value =
                Resource.ValidationError<Int>(R.string.msg_enter_valid_cvv_number)
            else -> {

            }
        }
    }
}