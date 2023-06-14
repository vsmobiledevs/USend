package com.usend.viewmodels
import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.R
import com.usend.comman.Resource
import com.usend.repository.AuthenticationRepository
import com.usend.base.BaseViewModel
import com.usend.extensions.checkInternetConnected
import com.usend.extensions.isValidEmail
import com.usend.extensions.nullSafe
import com.usend.utils.CommonUtils

class SignUpViewModel(application: Application, private var repository: AuthenticationRepository):
    BaseViewModel(application){

    val mContext: Context = application.applicationContext

    val status : MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val email by lazy { MediatorLiveData<String>() }
    val password by lazy { MediatorLiveData<String>() }
    val re_password by lazy { MediatorLiveData<String>() }
    val firstName by lazy { MediatorLiveData<String>() }
    val lastName by lazy { MediatorLiveData<String>() }
    val cbTermsAndPolicy by lazy { MediatorLiveData<Boolean>() }

    fun signUp()
    {
        when {
            !mContext.checkInternetConnected() -> status.value = Resource.NoInternetError<Int>(R.string.default_internet_message)
            email.value.isNullOrEmpty() -> status.value = Resource.ValidationError<Int>(R.string.msg_please_enter_email)
            !email.value!!.isValidEmail() -> status.value = Resource.ValidationError<Int>(R.string.msg_valid_email)
            firstName.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_first_name_can_not_be_blank)
            lastName.value.isNullOrEmpty() -> status.value =
                Resource.ValidationError<Int>(R.string.msg_last_name_can_not_be_blank)
            password.value.isNullOrEmpty() -> status.value = Resource.ValidationError<String>(R.string.msg_please_enter_password)
            !CommonUtils.isValidPassword(password.value!!) -> status.value = Resource.ValidationError<Int>(R.string.msg_password_strength)
            password.value != re_password.value -> status.value = Resource.ValidationError<Int>(R.string.msg_password_does_not_match)
            cbTermsAndPolicy.value == null || !cbTermsAndPolicy.value!! -> status.value = Resource.ValidationError<Int>(R.string.msg_accept_terms_policy)
            else -> {
                status.addSource(
                    repository.checkEmail(email = email.value.nullSafe())) {
                    if (it is Resource.Success<*>) {

                    }
                    status.value = it
                }
            }
        }
    }
}