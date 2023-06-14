/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 https://www.spaceotechnologies.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.usend.repository

import androidx.lifecycle.MutableLiveData
import com.base.network.ApiClient
import com.base.network.api.CountryApi
import com.base.network.api.UserApi
import com.base.network.model.GetCountryResponse
import com.base.network.model.GetUSAStateList
import com.base.network.model.SignUpReponse
import com.base.network.model.SuccessModel
import com.usend.comman.Resource
import com.usend.utils.JLog
import com.usend.utils.SERVER_ERROR
import com.usend.utils.SUCCESS
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File

object AuthenticationRepository {

    private val authClient: UserApi by lazy {

        ApiClient().createService(UserApi::class.java)
    }

    private val countryClient: CountryApi by lazy {

        ApiClient().createService(CountryApi::class.java)
    }

    init {

        JLog.e("AMIT", "AuthenticationRepository created")
    }

    fun signUp(
        vFirstName: String,
        vLastName: String,
        email: String,
        password: String,
        country: String,
        countryCodeId: Int,
        phone: String,
        state: String,
        city: String,
        addressLine1: String,
        addressLine2: String,
        zipCode: String,
        device_token: String,
        device_type: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall =
            authClient.signUp(
                firstName = vFirstName,
                lastName = vLastName,
                email = email,
                password = password,
                phone = phone,
                state = state,
                city = city,
                street1 = addressLine1,
                street2 = addressLine2,
                postalCode = zipCode,
                deviceToken = device_token,
                deviceType = device_type,
                countryCodeId = countryCodeId
            )

        data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SignUpReponse> {
            override fun onResponse(call: Call<SignUpReponse>, response: Response<SignUpReponse>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SignUpReponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SignUpReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun checkEmail(email: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall =
            authClient.checkEmail(
                email = email
            )

        data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SuccessModel> {
            override fun onResponse(call: Call<SuccessModel>, response: Response<SuccessModel>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun signIn(email: String, password: String, token: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall =
            authClient.signIn(
                email = email,
                password = password,
                deviceToken = token,
                deviceType = "android"
            )

        data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SignUpReponse> {
            override fun onResponse(call: Call<SignUpReponse>, response: Response<SignUpReponse>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SignUpReponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SignUpReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun forgotPassword(email: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall =
            authClient.forgotPassword(
                email = email
            )

        data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SuccessModel> {
            override fun onResponse(call: Call<SuccessModel>, response: Response<SuccessModel>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun verifyOtp(otp: String, authToken : String, device_token: String,
                  device_type: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall =
            authClient.verifyOTP(
                otp = otp,
                authorization = authToken,
                deviceType = device_type,
                deviceToken = device_token
            )

        data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SignUpReponse> {
            override fun onResponse(call: Call<SignUpReponse>, response: Response<SignUpReponse>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SignUpReponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.VerifyOTPSuccess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SignUpReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun resendOtp(authToken : String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall =
            authClient.resendOTP(authorization = authToken)

        data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SignUpReponse> {
            override fun onResponse(call: Call<SignUpReponse>, response: Response<SignUpReponse>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SignUpReponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SignUpReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun updateMobile(authToken : String, phone: String, phoneCode: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall =
            authClient.updateMobileNumber(authorization = authToken, phone = phone)

        data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SignUpReponse> {
            override fun onResponse(call: Call<SignUpReponse>, response: Response<SignUpReponse>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SignUpReponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.UpdateMobileSuccess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SignUpReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getCountryList(): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = countryClient.getCountryList()

        data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<GetCountryResponse> {
            override fun onResponse(call: Call<GetCountryResponse>, response: Response<GetCountryResponse>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: GetCountryResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.GetCountryListSucess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<GetCountryResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getStateList(): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = countryClient.usaStateList()

        data.value = Resource.Loading<Boolean>(false)

        authClientCall.enqueue(object : retrofit2.Callback<GetUSAStateList> {
            override fun onResponse(call: Call<GetUSAStateList>, response: Response<GetUSAStateList>) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: GetUSAStateList? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Resource.GetStateListSucess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
                else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<GetUSAStateList>, t: Throwable) {
                t.printStackTrace()
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    private fun getMediaPart(file: File): MultipartBody.Part? {

        if (file != null) {
            val reqFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            return MultipartBody.Part.create(reqFile)
        } else {
            return null
        }
    }
}