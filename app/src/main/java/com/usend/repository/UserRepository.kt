package com.usend.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.base.network.ApiClient
import com.base.network.api.*
import com.base.network.model.*
import com.usend.comman.Resource
import com.usend.comman.Resource.*
import com.usend.extensions.error
import com.usend.extensions.toObject
import com.usend.utils.*
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import java.math.BigDecimal
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object UserRepository {
    private val packageClient: PackageApi by lazy {

        ApiClient().createService(PackageApi::class.java)
    }

    private val userClient: UserApi by lazy {

        ApiClient().createService(UserApi::class.java)
    }

    private val contentPagesClient: CmsPagesApi by lazy {

        ApiClient().createService(CmsPagesApi::class.java)
    }

    private val conciergeClient: ConciergeRequestApi by lazy {

        ApiClient().createService(ConciergeRequestApi::class.java)
    }

    private val countryClient: CountryApi by lazy {

        ApiClient().createService(CountryApi::class.java)
    }

    private val shippingAddressApi: ShippingAddressApi by lazy {

        ApiClient().createService(ShippingAddressApi::class.java)
    }

    private val shippingMethodApi: ShipStationApi by lazy {

        ApiClient().createService(ShipStationApi::class.java)
    }

    private val notificationApi: NotificationApi by lazy {

        ApiClient().createService(NotificationApi::class.java)
    }

    private val conciergePaymentApi: ConciergePaymentApi by lazy {

        ApiClient().createService(ConciergePaymentApi::class.java)
    }

    private val orderApi: OrderApi by lazy {

        ApiClient().createService(OrderApi::class.java)
    }

    private val subApi: SubscriptionsApi by lazy {

        ApiClient().createService(SubscriptionsApi::class.java)
    }

    val stripeCardApi: UsendCardApi by lazy {

        ApiClient().createService(UsendCardApi::class.java)
    }
    private val squareupCardApi: SquareUpCardApi by lazy {

        ApiClient().createServiceSquareup(SquareUpCardApi::class.java)
    }
    private val squareSubscriptionApi: SquareUpCardApi by lazy {

        ApiClient().createServiceSquareup(SquareUpCardApi::class.java)
    }


    fun getPackageList(authToken: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val packageCall = packageClient.packageList(authorization = authToken)

        data.value = Loading<Boolean>(true)

        packageCall.enqueue(object : retrofit2.Callback<ListPackageResponse> {
            override fun onResponse(
                call: Call<ListPackageResponse>,
                response: Response<ListPackageResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: ListPackageResponse? = response.body()
                    when (mBean?.responseCode) {
                        SUCCESS -> {
                            data.value = Success(mBean)
                        }
                        NOT_FOUND -> {
                            data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                        }
                        UNAUTHORISED -> {
                            data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                        }
                        else -> {
                            data.value = Resource.Error<String>(mBean?.responseMessage!!)
                        }
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<ListPackageResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getPackageDetails(authToken: String, id: Int): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val packageCall = packageClient.packageDetails(authorization = authToken, id = id)

        data.value = Loading<Boolean>(true)

        packageCall.enqueue(object : retrofit2.Callback<PackageDetailResponse> {
            override fun onResponse(
                call: Call<PackageDetailResponse>,
                response: Response<PackageDetailResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: PackageDetailResponse? = response.body()
                    when (mBean?.responseCode) {
                        SUCCESS -> {
                            data.value = Success(mBean)
                        }
                        NOT_FOUND -> {
                            data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                        }
                        UNAUTHORISED -> {
                            data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                        }
                        else -> {
                            data.value = Resource.Error<String>(mBean?.responseMessage!!)
                        }
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<PackageDetailResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun splitPackage(authToken: String, id: Int, count: Int): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val packageCall =
            packageClient.splitPackage(authorization = authToken, id = id, splitCount = count)

        data.value = Loading<Boolean>(true)

        packageCall.enqueue(object : retrofit2.Callback<SuccessModel> {
            override fun onResponse(
                call: Call<SuccessModel>,
                response: Response<SuccessModel>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    when (mBean?.responseCode) {
                        SUCCESS -> {
                            data.value = SplitSuccess(mBean)
                        }
                        NOT_FOUND -> {
                            data.value = ItemsNotFound<String>(mBean.responseMessage)
                        }
                        UNAUTHORISED -> {
                            data.value = UnAuthorisedRequest<String>(mBean.responseMessage)
                        }
                        else -> {
                            data.value = Resource.Error<String>(mBean?.responseMessage!!)
                        }
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getProfileData(authToken: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val packageCall = userClient.getUserProfile(authorization = authToken)

        data.value = Loading<Boolean>(true)

        packageCall.enqueue(object : retrofit2.Callback<SignUpReponse> {
            override fun onResponse(call: Call<SignUpReponse>, response: Response<SignUpReponse>) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SignUpReponse? = response.body()
                    when (mBean?.responseCode) {
                        SUCCESS -> {
                            data.value = Success(mBean)
                        }
                        NOT_FOUND -> {
                            data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                        }
                        UNAUTHORISED -> {
                            data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                        }
                        else -> {
                            data.value = Resource.Error<String>(mBean?.responseMessage!!)
                        }
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SignUpReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun updateProfileData(
        authToken: String,
        vFirstName: String,
        vLastName: String,
        email: String,
        countryId: Int,
        phone: String,
        state: String,
        city: String,
        addressLine1: String,
        addressLine2: String,
        zipCode: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val packageCall = userClient.editUserProfile(
            authorization = authToken,
            firstName = vFirstName,
            lastName = vLastName,
            email = email,
            street1 = addressLine1,
            street2 = addressLine2,
            postalCode = zipCode,
            state = state,
            city = city,
            phone = phone,
            countryCodeId = countryId
        )

        data.value = Loading<Boolean>(true)

        packageCall.enqueue(object : retrofit2.Callback<SignUpReponse> {
            override fun onResponse(call: Call<SignUpReponse>, response: Response<SignUpReponse>) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SignUpReponse? = response.body()
                    when (mBean?.responseCode) {
                        SUCCESS -> {
                            data.value = Success(mBean)
                        }
                        NOT_FOUND -> {
                            data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                        }
                        UNAUTHORISED -> {
                            data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                        }
                        else -> {
                            data.value = Resource.Error<String>(mBean?.responseMessage!!)
                        }
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SignUpReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun changePassword(
        authToken: String,
        currentPassword: String,
        newPassword: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val packageCall = userClient.changePassword(
            authorization = authToken,
            currentPassword = currentPassword,
            password = newPassword
        )

        data.value = Loading<Boolean>(true)

        packageCall.enqueue(object : retrofit2.Callback<SuccessModel> {
            override fun onResponse(call: Call<SuccessModel>, response: Response<SuccessModel>) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun changeUnitSystem(
        authToken: String,
        unit_system: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val packageCall = userClient.changeUnit(
            authorization = authToken,
            unitSystem = unit_system
        )

        data.value = Loading<Boolean>(true)

        packageCall.enqueue(object : retrofit2.Callback<SuccessModel> {
            override fun onResponse(call: Call<SuccessModel>, response: Response<SuccessModel>) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = ChangeUnitSuccess(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getShipToAddressList(
        authToken: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val shipToAddressCall = shippingAddressApi.shippingAddressList(authorization = authToken)

        data.value = Loading<Boolean>(true)

        shipToAddressCall.enqueue(object : retrofit2.Callback<ListAddressResponse> {
            override fun onResponse(
                call: Call<ListAddressResponse>,
                response: Response<ListAddressResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: ListAddressResponse? = response.body()
                    when (mBean?.responseCode) {
                        SUCCESS -> {
                            data.value = Success(mBean)
                        }
                        NOT_FOUND -> {
                            data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                        }
                        UNAUTHORISED -> {
                            data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                        }
                        else -> {
                            data.value = Resource.Error<String>(mBean?.responseMessage!!)
                        }
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<ListAddressResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun addShippingAddress(
        label: String,
        receiverName: String,
        addressLine1: String,
        addressLine2: String,
        state: String,
        city: String,
        zipcode: String,
        countryCodeId: Int,
        phone: String,
        authToken: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val shipToAddressCall = shippingAddressApi.createShippingAddresses(
            label = label,
            receiverName = receiverName,
            countryCodeId = countryCodeId,
            phone = phone,
            state = state,
            city = city,
            street1 = addressLine1,
            street2 = addressLine2,
            postalCode = zipcode,
            isBillingAddress = false,
            authorization = authToken
        )

        data.value = Loading<Boolean>(true)

        shipToAddressCall.enqueue(object : retrofit2.Callback<CreateAddressResponse> {
            override fun onResponse(
                call: Call<CreateAddressResponse>,
                response: Response<CreateAddressResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: CreateAddressResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<CreateAddressResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun updateShippingAddress(
        id: Int,
        label: String,
        receiverName: String,
        addressLine1: String,
        addressLine2: String,
        state: String,
        city: String,
        zipcode: String,
        countryCodeId: Int,
        phone: String,
        authToken: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val shipToAddressCall = shippingAddressApi.editShippingAddresses(
            label = label,
            receiverName = receiverName,
            countryCodeId = countryCodeId,
            phone = phone,
            state = state,
            city = city,
            street1 = addressLine1,
            street2 = addressLine2,
            postalCode = zipcode,
            isBillingAddress = false,
            authorization = authToken,
            id = id
        )

        data.value = Loading<Boolean>(true)

        shipToAddressCall.enqueue(object : retrofit2.Callback<EditShippingAddressReponse> {
            override fun onResponse(
                call: Call<EditShippingAddressReponse>,
                response: Response<EditShippingAddressReponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: EditShippingAddressReponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = EditAddressSuccess(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<EditShippingAddressReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun deleteShippingAddress(authToken: String, id: Int): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val shipToAddressCall =
            shippingAddressApi.deleteShippingAddress(id = id, authorization = authToken)

        data.value = Loading<Boolean>(true)

        shipToAddressCall.enqueue(object : retrofit2.Callback<SuccessModel> {
            override fun onResponse(
                call: Call<SuccessModel>,
                response: Response<SuccessModel>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = DeleteAddressSuccess(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getShippingMethodList(): MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        val shippingMethodCall = shippingMethodApi.getShippingList()
        // data.value = Resource.Loading<Boolean>(true)
        shippingMethodCall.enqueue(object : retrofit2.Callback<ShippingListResponse> {
            override fun onResponse(
                call: Call<ShippingListResponse>,
                response: Response<ShippingListResponse>
            ) {
                //data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: ShippingListResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = GetCarrierListSucess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<ShippingListResponse>, t: Throwable) {
                t.printStackTrace()
                JLog.e("error", t.localizedMessage)
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getShippingMethods(
        authToken: String,
        package_ids: String,
        address_id: Int,
        carrier_code : String,
        profile_filter : String,
        carrierList: List<CarrierList>,
        observer: MutableLiveData<Any>
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()
        observer.postValue(Loading<Boolean>(true))

        Observable.fromIterable(carrierList)
            .flatMap { t ->
                val response = shippingMethodApi.getCarrierRateList(
                    authorization = authToken,
                    addressId = address_id,
                    packageId = package_ids,
                    carrierCode = t.code,
                    priceFilter = profile_filter
                ).execute().body()!!
                return@flatMap just(response)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<ListGetRatesResponse?> {
                override fun onSubscribe(d: Disposable) {
                    //todo for future use
                }

                override fun onNext(t: ListGetRatesResponse) {
                    val mBean: ListGetRatesResponse = t
                    if (mBean.responseCode == SUCCESS||mBean.responseCode== SUCCESS_ERROR) {
                        observer.value = Success(mBean)
                    } else if (mBean.responseCode == NOT_FOUND) {
                        observer.value = ItemsNotFound<String>(mBean.responseMessage!!)
                    } else if (mBean.responseCode == UNAUTHORISED) {
                        observer.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        observer.value = Resource.Error<String>(mBean.responseMessage!!)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    JLog.e("error", e.localizedMessage!!)
                    data.value = Loading<Boolean>(false)
                    observer.postValue(e.message?.let { Resource.Error<String>(SERVER_ERROR) })
                }

                override fun onComplete() {
                    //todo for future use
                }
            })
        observer.postValue(Loading<Boolean>(false))
        return data
    }


    fun getShippingList(): MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        val shippingMethodCall = shippingMethodApi.getShippingList()
        data.value = Resource.Loading<Boolean>(true)
        shippingMethodCall.enqueue(object : retrofit2.Callback<ShippingListResponse> {
            override fun onResponse(
                call: Call<ShippingListResponse>,
                response: Response<ShippingListResponse>
            ) {
                data.value = Resource.Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: ShippingListResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<ShippingListResponse>, t: Throwable) {
                t.printStackTrace()
                JLog.e("error", t.localizedMessage)
                data.value = Resource.Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }


    fun getShippingDetails(
        authToken: String,
        service_name: String,
        package_Ids: String,
        address_id: Int,
        cost: Double,
        carrierCode: String,
        serviceCode: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val shipToAddressCall = shippingAddressApi.shippingDetails(
            authorization = authToken, id = address_id, serviceName = service_name,
            shipmentCost = cost.toBigDecimal(), packageId = package_Ids,
            carrierCode = carrierCode, serviceCode = serviceCode
        )

        data.value = Loading<Boolean>(true)

        shipToAddressCall.enqueue(object : retrofit2.Callback<ShippingDetailsResponse> {
            override fun onResponse(
                call: Call<ShippingDetailsResponse>,
                response: Response<ShippingDetailsResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: ShippingDetailsResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<ShippingDetailsResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getOrderLists(authToken: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val shipToAddressCall = orderApi.orderList(authorization = authToken)

        data.value = Loading<Boolean>(true)

        shipToAddressCall.enqueue(object : retrofit2.Callback<OrderListResponse> {
            override fun onResponse(
                call: Call<OrderListResponse>,
                response: Response<OrderListResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: OrderListResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<OrderListResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getOrderDetails(
        authToken: String,
        order_id: Int
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val shipToAddressCall = orderApi.getOrder(authorization = authToken, id = order_id)

        data.value = Loading<Boolean>(true)

        shipToAddressCall.enqueue(object : retrofit2.Callback<GetOrderResponse> {
            override fun onResponse(
                call: Call<GetOrderResponse>,
                response: Response<GetOrderResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: GetOrderResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<GetOrderResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun createOrder(
        authToken: String,
        address_id: Int,
        packageId: String,
        amountPaid: String,
        shippingAmount: String,
        carrierCode: String,
        serviceCode: String,
        additional_fee: String,
        requesteShippingService: String,
        additionalChargeId: String,
        card_id: String,
        notes: String,
        declaredValue: String,
        packageContent: String,
        consolidatedFees: BigDecimal
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val createAddressCall = orderApi.createOrder(
            authorization = authToken,
            addressId = address_id,
            packageId = packageId,
            amountPaid = amountPaid,
            additionalFee = additional_fee,
            shippingAmount = shippingAmount,
            carrierCode = carrierCode,
            serviceCode = serviceCode,
            cardId = card_id,
            requesteShippingService = requesteShippingService,
            additionalChargeId = additionalChargeId,
            notes = notes,
            declaredValue = declaredValue,
            packageContent = packageContent,
            consolidatedFees = consolidatedFees
        )

        data.value = Loading<Boolean>(true)

        createAddressCall.enqueue(object : retrofit2.Callback<OrderResponse> {
            override fun onResponse(
                call: Call<OrderResponse>,
                response: Response<OrderResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: OrderResponse? = response.body()
                    when (mBean?.responseCode) {
                        SUCCESS -> {
                            data.value = CreateOrderSucess(mBean)
                        }
                        NOT_FOUND -> {
                            data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                        }
                        UNAUTHORISED -> {
                            data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                        }
                        else -> {
                            data.value = Resource.Error<String>(mBean?.responseMessage!!)
                        }
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }


            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getCountryList(): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = countryClient.getCountryList()

        // data.value = Resource.Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<GetCountryResponse> {
            override fun onResponse(
                call: Call<GetCountryResponse>,
                response: Response<GetCountryResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: GetCountryResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = GetCountryListSucess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<GetCountryResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getStateList(): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = countryClient.usaStateList()

        data.value = Loading<Boolean>(false)

        authClientCall.enqueue(object : retrofit2.Callback<GetUSAStateList> {
            override fun onResponse(
                call: Call<GetUSAStateList>,
                response: Response<GetUSAStateList>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: GetUSAStateList? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = GetStateListSucess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<GetUSAStateList>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getAdditionalServices(): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = shippingAddressApi.getAdditionalServiceCharge()

        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<GetAdditionalServiceChargeResponse> {

            override fun onResponse(
                call: Call<GetAdditionalServiceChargeResponse>,
                response: Response<GetAdditionalServiceChargeResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: GetAdditionalServiceChargeResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = AdditionalServicesSucess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<GetAdditionalServiceChargeResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getContentPages(type: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = contentPagesClient.cmsPages(type = type)

        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<CmsPageResponse> {

            override fun onResponse(
                call: Call<CmsPageResponse>,
                response: Response<CmsPageResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: CmsPageResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<CmsPageResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getConciergeList(authToken: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = conciergeClient.listConciergeRequest(authorization = authToken)
        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<ListConciergeRequestResponse> {

            override fun onResponse(
                call: Call<ListConciergeRequestResponse>,
                response: Response<ListConciergeRequestResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: ListConciergeRequestResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<ListConciergeRequestResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getShippingCalculator(
        countryCodeId: Int,
        city: String,
        zipCode: String,
        weight: Double,
        weight_unit: String,
        length: Double,
        width: Double,
        height: Double,
        dimensionUnit: String,
        price_filter: String,
        carrierList: List<CarrierList>,
        observer: MutableLiveData<Any>
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()
        observer.postValue(Loading<Boolean>(true))

        Observable.fromIterable(carrierList)
            .flatMap { t ->
                val response = shippingMethodApi.shippingCalculatorRates(
                    countryCodeId = countryCodeId,
                    city = city,
                    postalCode = zipCode,
                    weight = weight.toBigDecimal(),
                    weightUnit = weight_unit,
                    length = length.toBigDecimal(),
                    width = width.toBigDecimal(),
                    height = height.toBigDecimal(),
                    dimensionUnit = dimensionUnit,
                    carrierCode = " ",
                    price_filter = price_filter,
                    single_carrier_code = t.code
                ).execute().body()!!
                return@flatMap just(response)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<ListshippingCalculatorRates?> {
                override fun onSubscribe(d: Disposable) {
                    //todo for future use
                }

                override fun onNext(t: ListshippingCalculatorRates) {
                    val mBean: ListshippingCalculatorRates = t
                    if (mBean.responseCode == SUCCESS ||mBean.responseCode== SUCCESS_ERROR) {
                        observer.postValue(Success(mBean))
                    } else if (mBean.responseCode == NOT_FOUND) {
                        observer.postValue(ItemsNotFound<String>(mBean.responseMessage!!))
                    } else {
                        observer.postValue(Resource.Error<String>(mBean.responseMessage!!))
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    JLog.e("error", e.localizedMessage!!)
                    data.value = Loading<Boolean>(false)
                    observer.postValue(e.message?.let { Resource.Error<String>(SERVER_ERROR) })
                }

                override fun onComplete() {
                    //todo for future use
                }
            })
        observer.postValue(Loading<Boolean>(false))
        return data
    }

    fun contactUsApi(
        email: String,
        name: String,
        msg: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = contentPagesClient.contactUsInfo(
            email = email,
            message = msg,
            name = name
        )
        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SuccessModel> {

            override fun onResponse(
                call: Call<SuccessModel>,
                response: Response<SuccessModel>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getReasonList(): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = contentPagesClient.getReasonList()
        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<GetReasonResponse> {

            override fun onResponse(
                call: Call<GetReasonResponse>,
                response: Response<GetReasonResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: GetReasonResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = GetReasonsSuccess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<GetReasonResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun createConciergeReq(
        authToken: String,
        desc: String,
        cost: Float,
        productName: String,
        webSiteLink: String,
        productImage: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = conciergeClient.newConciergeRequest(
            authorization = authToken,
            description = desc,
            price = cost,
            productName = productName,
            websiteUrl = webSiteLink,
            productImage = productImage
        )

        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<ConciergeRequestResponse> {

            override fun onResponse(
                call: Call<ConciergeRequestResponse>,
                response: Response<ConciergeRequestResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: ConciergeRequestResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<ConciergeRequestResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun logout(authKey: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = userClient.logOut(authorization = authKey)
        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SuccessModel> {

            override fun onResponse(
                call: Call<SuccessModel>,
                response: Response<SuccessModel>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = LogoutSuccess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun deleteUser(authKey: String,userId:Int): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = userClient.deleteUser(authorization = authKey, useId = userId)
        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<SuccessModel> {

            override fun onResponse(
                call: Call<SuccessModel>,
                response: Response<SuccessModel>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = LogoutSuccess(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }


    fun uspsVerification(
        authKey: String,
        passport_image: String,
        driving_image: String,
        file_name: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = userClient.addUspsVerification(
            authorization = authKey,
            drivingImage = driving_image,
            passportImage = passport_image,
            fileName = file_name
        )
        data.value = Loading<Boolean>(true)

        authClientCall.enqueue(object : retrofit2.Callback<AddUspsDetailsResponse> {

            override fun onResponse(
                call: Call<AddUspsDetailsResponse>,
                response: Response<AddUspsDetailsResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: AddUspsDetailsResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<AddUspsDetailsResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun getNotificationList(authToken: String, isLoading: Boolean): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val authClientCall = notificationApi.listNotification(authorization = authToken)
        data.value = Loading<Boolean>(isLoading)

        authClientCall.enqueue(object : retrofit2.Callback<NotificationsListReponse> {

            override fun onResponse(
                call: Call<NotificationsListReponse>,
                response: Response<NotificationsListReponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: NotificationsListReponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = NotiListSuccess(mBean)
                    } else if (mBean?.responseCode == NOT_FOUND) {
                        data.value = ItemsNotFound<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                } else {
                    data.value = Resource.Error<String>(SERVER_ERROR)
                }
            }

            override fun onFailure(call: Call<NotificationsListReponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun readNotification(authToken: String, id: Int): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = notificationApi.readNotification(
            authorization = authToken,
            notificationId = id
        )

        data.value = Loading<Boolean>(false)

        call.enqueue(object : retrofit2.Callback<SuccessModel> {
            override fun onResponse(
                call: Call<SuccessModel>,
                response: Response<SuccessModel>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = ReadNotificationSuccess(mBean)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun deleteNotification(authToken: String, id: Int, type: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = notificationApi.notificationsRemoveNotificationDelete(
            authorization = authToken,
            notificationId = id, type = type
        )

        data.value = Loading<Boolean>(false)

        call.enqueue(object : retrofit2.Callback<SuccessModel> {
            override fun onResponse(
                call: Call<SuccessModel>,
                response: Response<SuccessModel>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: SuccessModel? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = DeleteNotificationSuccess(mBean)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
            }

            override fun onFailure(call: Call<SuccessModel>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun payConcierge(
        card_id: String,
        payable_amount: Float,
        id: Int,
        authToken: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = conciergePaymentApi.createPayment(
            cardId = card_id,
            payableAmount = payable_amount, conciergeId = id, authorization = authToken
        )

        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<CreatePaymentResponse> {
            override fun onResponse(
                call: Call<CreatePaymentResponse>,
                response: Response<CreatePaymentResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: CreatePaymentResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = ConciergePayment(mBean)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
            }

            override fun onFailure(call: Call<CreatePaymentResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun updateConciergeReq(id: Int, payment_id: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = conciergeClient.updateConciergeStatus(
            conciergeId = id,
            paymentIntentId = payment_id
        )

        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<GetConciergeRequestResponse> {
            override fun onResponse(
                call: Call<GetConciergeRequestResponse>,
                response: Response<GetConciergeRequestResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: GetConciergeRequestResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = UpdateConciergeReqSuccess(mBean)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
            }

            override fun onFailure(call: Call<GetConciergeRequestResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun purchaseSubscription(
        authKey: String,
        card_Id: String,
        plan_type: String,
        start_date: String,
        end_date: String,
        amount: Float,
        subscriptionID: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = subApi.createTransaction(
            authorization = authKey,
            userType = "android",
            base64Receipt = null,
            purchaseSku = null,
            purchaseToken = null,
            cardId = card_Id,
            plan_type = plan_type,
            startDate = start_date,
            endDate = end_date,
            amount = amount,
            webSubscriptionId = subscriptionID,
        )

        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<CreateTransactionResponse> {
            override fun onResponse(
                call: Call<CreateTransactionResponse>,
                response: Response<CreateTransactionResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: CreateTransactionResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = Success(mBean)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
            }

            override fun onFailure(call: Call<CreateTransactionResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun cancelSubscriptionusen(
        authKey: String,
        user_Id: Int,
        end_date: String,
        subscriptionID: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = subApi.cancelSubscriptionUsend(
            authorization = authKey, userID = user_Id,
            chargedThrough_date = end_date, webSubscriptionId = subscriptionID,
        )

        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<CancelSubscriptionUsendResponse> {
            override fun onResponse(
                call: Call<CancelSubscriptionUsendResponse>,
                response: Response<CancelSubscriptionUsendResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: CancelSubscriptionUsendResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = SuccessCancel(mBean)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
            }

            override fun onFailure(call: Call<CancelSubscriptionUsendResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun createSubscriptionSquareUp(
        authKey: String,
        createSubscription: CreateSubscription,
        card_Id: String,
        plan_type: String,
        amount: Float
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = squareSubscriptionApi.createSubscriptions(
            authorization = authKey,
            createSubscription
        )

        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<SubscriptionResponse> {
            override fun onResponse(
                call: Call<SubscriptionResponse>,
                response: Response<SubscriptionResponse>
            ) {
                data.value = Loading<Boolean>(false)
                if (response.isSuccessful) {
                    data.value = Loading<Boolean>(false)
                    val mBean: SubscriptionResponse? = response.body()
                    data.value = Success(mBean)
                    if (mBean != null) {
                        val subscriptionID = mBean.subscription.id
                        val startdate = mBean.subscription.start_date
                        if (mBean.subscription.charged_through_date != null) {
                            val endDate = mBean.subscription.charged_through_date
                        } else {
                            val endDate = mBean.subscription.canceled_date
                        }

                        /*   if (startdate!=null)
                           {
                               val endDate=  ConvertToDate(startdate);
                               Log.e("tag", endDate!!.toString())
                           }*/

                        val endDate = "2022-06-16"
                        val token = PreferenceHelper.getUserObject().authenticationToken


                        purchaseSubscription(
                            token!!,
                            card_Id, plan_type, startdate, endDate, amount, subscriptionID
                        )
                    }

                } else {
                    data.value = Loading<Boolean>(false)
                    val errordata = response.errorBody()?.source()?.buffer()?.snapshot()?.utf8()
                        ?.toObject<SquareupErrorResponse>()
                    data.value = SquareupError(errordata)
                }
            }

            override fun onFailure(call: Call<SubscriptionResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("tag", "error" + t.message.toString())
                data.value = Loading<Boolean>(false)
                data.value = t.message?.let { SquareupFailureError<String>(it) }
            }
        })
        return data
    }

    fun retriveSubscriptionSquareUp(subscriptionID: String): MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        val call = squareSubscriptionApi.retriveSubscriptions(
            SQUARE_UP_TOKEN, subscriptionID
        )
        data.value = Loading<Boolean>(true)
        call.enqueue(object : retrofit2.Callback<SubscriptionResponse> {
            override fun onResponse(
                call: Call<SubscriptionResponse>,
                response: Response<SubscriptionResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    data.value = Loading<Boolean>(false)
                    val mBean: SubscriptionResponse? = response.body()
                    data.value = Success(mBean)

                } else {
                    data.value = Loading<Boolean>(false)
                    val errordata = response.errorBody()?.source()?.buffer()?.snapshot()?.utf8()
                        ?.toObject<SquareupErrorResponse>()
                    data.value = SquareupError(errordata)

                }
            }

            override fun onFailure(call: Call<SubscriptionResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("tag", "error" + t.message.toString())
                data.value = Loading<Boolean>(false)
                data.value = t.message?.let { SquareupFailureError<String>(it) }
            }
        })
        return data
    }

    fun cancelSubscriptionSquareUp(subscriptionID: String): MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        val call = squareSubscriptionApi.cancelSubscriptions(
            SQUARE_UP_TOKEN,
            subscriptionID
        )
        data.value = Loading<Boolean>(true)
        call.enqueue(object : retrofit2.Callback<PlanCancelresponse> {
            override fun onResponse(
                call: Call<PlanCancelresponse>,
                response: Response<PlanCancelresponse>
            ) {
                data.value = Loading<Boolean>(false)
                if (response.isSuccessful) {
                    val mBean: PlanCancelresponse? = response.body()
                    data.value = SuccessCancel(mBean)
                    val token = PreferenceHelper.getUserObject().authenticationToken
                    val userID = PreferenceHelper.getUserObject().id
                    val end_date = mBean?.subscription?.canceled_date
                    cancelSubscriptionusen(token!!, userID!!, end_date!!, subscriptionID)
                } else {
                    data.value = Loading<Boolean>(false)
                    val errordata = response.errorBody()?.source()?.buffer()?.snapshot()?.utf8()
                        ?.toObject<SquareupErrorResponse>()
                    data.value = SquareupError(errordata)

                }
            }

            override fun onFailure(call: Call<PlanCancelresponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("tag", "error" + t.message.toString())
                data.value = Loading<Boolean>(false)
                data.value = t.message?.let { SquareupFailureError<String>(it) }
            }
        })
        return data
    }

    fun addSqureupCard(
        authKey: String,
        createcardList: CreateCard,
        add: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()
        val call = squareupCardApi.addSquareUpCard(authKey, createcardList)
        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<SquareupCreateResponse> {
            override fun onResponse(
                call: Call<SquareupCreateResponse>,
                response: Response<SquareupCreateResponse>
            ) {
                if (response.code() == 200) {
                    data.value = Loading<Boolean>(false)
                    try {
                        if (response.isSuccessful) {
                            val mBean: SquareupCreateResponse? = response.body()

                            if (add == ADD) {
                                Log.e("tag", "mBeanadd" + mBean)
                                data.value = AddUsendCard(mBean)
                            } else {
                                data.value = UpdateSquareupCard(mBean)
                            }
                            val brand = mBean?.card!!.card_brand
                            val cardId = mBean.card.id
                            val expiryMonth = mBean.card.exp_month
                            val expiryYear = mBean.card.exp_year
                            val fingerPrint = mBean.card.fingerprint
                            val createdAt = mBean.card.created_at
                            val last4 = mBean.card.last_4
                            val updateAt = " "
                            val userId = mBean.card.customer_id
                            val token = PreferenceHelper.getUserObject().authenticationToken
                            Log.e("resulttoken", token.toString())

                            val addCardUsend = AddCardUsend(
                                brand!!,
                                cardId!!,
                                createdAt!!,
                                "",
                                expiryMonth!!,
                                expiryYear!!,
                                fingerPrint!!,
                                last4!!,
                                updateAt,
                                userId!!
                            )
                            if (token != null) {
                                addUsendCard(token, addCardUsend, add)
                            }

                        }
                    } catch (e: Exception) {
                        Log.e("tag", e.message.toString())
                    }

                } else {
                    data.value = Loading<Boolean>(false)
                    //  Log.v("rese",call.request().url().toString())
                    val errordata = response.errorBody()?.source()?.buffer()?.snapshot()?.utf8()
                        ?.toObject<SquareupErrorResponse>()
                    data.value = SquareupError(errordata)

                }
            }


            override fun onFailure(call: Call<SquareupCreateResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("tag", "error" + t.printStackTrace().toString())
                //  Log.v("rese",call.request().url().toString())
                data.value = Loading<Boolean>(false)
                data.value = t.message?.let { SquareupFailureError<String>(it) }
            }
        })
        return data
    }


    fun listSquareupCard(authKey: String, customerId: String): MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        val call = squareupCardApi.listSquareUpCard(authKey, customerId)
        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<SquareUpCardList> {
            override fun onResponse(
                call: Call<SquareUpCardList>,
                response: Response<SquareUpCardList>
            ) {
                data.value = Loading<Boolean>(false)
                if (response.isSuccessful) {
                    val mBean: SquareUpCardList? = response.body()
                    data.value = AddSquareupCard(mBean)
                } else {
                    data.value = Loading<Boolean>(false)
                    val errordata = response.errorBody()?.source()?.buffer()?.snapshot()?.utf8()
                        ?.toObject<SquareupErrorResponse>()
                    data.value = SquareupError(errordata)

                }
            }

            override fun onFailure(call: Call<SquareUpCardList>, t: Throwable) {
                t.printStackTrace()
                Log.e("tag", "error" + t.message.toString())
                data.value = Loading<Boolean>(false)
                data.value = t.message?.let { SquareupFailureError<String>(it) }
            }
        })
        return data
    }

    fun addUsendCard(
        authKey: String,
        addCardUsend: AddCardUsend,
        add: String
    ): MutableLiveData<Any> {
        val data = MutableLiveData<Any>()
        val call = stripeCardApi.addUsendCard(authKey, addCardUsend)
        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<UsendAddResponse> {
            override fun onResponse(
                call: Call<UsendAddResponse>,
                response: Response<UsendAddResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: UsendAddResponse? = response.body()
                    if (add == ADD) {
                        data.value = AddUsendCard(mBean)

                    } else {
                        data.value = UpdateSquareupCard(mBean)
                        Log.e("resultupdate", mBean.toString())
                        val token = PreferenceHelper.getUserObject().authenticationToken
                        if (token != null) {
                            deleteSquareCard(SQUARE_UP_TOKEN, add)
                        }
                    }
                } else {
                    val mBean: UsendAddResponse? = response.body()
                    data.value = Resource.Error<String>(mBean?.responseMessage!!)
                }

            }

            override fun onFailure(call: Call<UsendAddResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }


    fun deleteSquareCard(authKey: String, card_id: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()
        val call = squareupCardApi.deleteSquareUpCard(authKey, card_id)
        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<Squareupdelete> {
            override fun onResponse(
                call: Call<Squareupdelete>,
                response: Response<Squareupdelete>
            ) {
                data.value = Loading<Boolean>(false)
                if (response.code() == 200) {
                    if (response.isSuccessful) {
                        val mBean: Squareupdelete? = response.body()
                        data.value = DeleteSquareupCard(mBean)
                        val token = PreferenceHelper.getUserObject().authenticationToken
                        if (token != null) {
                            deleteUsendCard(token, card_id)
                        }

                    }
                } else {
                    data.value = Loading<Boolean>(false)
                    val errordata = response.errorBody()?.source()?.buffer()?.snapshot()?.utf8()
                        ?.toObject<SquareupErrorResponse>()
                    data.value = SquareupError(errordata)
                }

            }

            override fun onFailure(call: Call<Squareupdelete>, t: Throwable) {
                t.printStackTrace()
                Log.e("tag", "error" + t.message.toString())
                data.value = Loading<Boolean>(false)
                data.value = t.message?.let { Resource.SquareupFailureError<String>(it) }
            }
        })
        return data
    }

    fun deleteUsendCard(authKey: String, card_id: String): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = stripeCardApi.squareUsendCardDelete(authKey, card_id)

        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<UsenCardDeleteResponse> {
            override fun onResponse(
                call: Call<UsenCardDeleteResponse>,
                response: Response<UsenCardDeleteResponse>
            ) {
                data.value = Loading<Boolean>(false)
                try {
                    if (response.isSuccessful) {
                        val mBean: UsenCardDeleteResponse? = response.body()
                        data.value = DeleteSquareupCard(mBean)
                    } else {
                        val mBean: UsenCardDeleteResponse? = response.body()
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)

                    }

                } catch (e: Exception) {
                    Log.e("tag", e.error().toString())
                }


            }

            override fun onFailure(call: Call<UsenCardDeleteResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }

    fun logsUsenddata(
        request: String,
        response: String,
        typeOfRequest: String,
        userID: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()
        val call = stripeCardApi.logsUsendCardMention(request, response, typeOfRequest, userID)
        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<UsenCardDeleteResponse> {
            override fun onResponse(
                call: Call<UsenCardDeleteResponse>,
                response: Response<UsenCardDeleteResponse>
            ) {
                data.value = Loading<Boolean>(false)
                try {
                    if (response.isSuccessful) {
                        val mBean: UsenCardDeleteResponse? = response.body()
                        data.value = DeleteSquareupCard(mBean)
                    } else {
                        val mBean: UsenCardDeleteResponse? = response.body()
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)

                    }

                } catch (e: Exception) {
                    Log.e("tag", e.error().toString())
                }


            }

            override fun onFailure(call: Call<UsenCardDeleteResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }


    fun ConvertToDate(dateString: String) {
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        val c = Calendar.getInstance()
        try {
            c.setTime(sdf.parse(dateString))
        } catch (e: ParseException) {
// TODO Auto-generated catch block
            e.printStackTrace()
        }
        c.add(Calendar.DATE, 30) //14 dats add

        sdf = SimpleDateFormat("yyyy-MM-dd")
        val resultdate = Date(c.getTimeInMillis())
        val newdateString = sdf.format(resultdate)
        System.out.println(newdateString)

    }

    fun updateOrderAndPayment(
        orderId: String, payment_id: String,
        payment_status: String, stripe_payment_id: String,
        authKey: String
    ): MutableLiveData<Any> {

        val data = MutableLiveData<Any>()

        val call = orderApi.updateOrderAndPayment(
            authorization = authKey,
            orderId = orderId, paymentId = payment_id, paymentIntentId = stripe_payment_id,
            paymentStatus = payment_status
        )

        data.value = Loading<Boolean>(true)

        call.enqueue(object : retrofit2.Callback<OrderListResponse> {
            override fun onResponse(
                call: Call<OrderListResponse>,
                response: Response<OrderListResponse>
            ) {
                data.value = Loading<Boolean>(false)

                if (response.isSuccessful) {
                    val mBean: OrderListResponse? = response.body()
                    if (mBean?.responseCode == SUCCESS) {
                        data.value = UpdateOrderSuccess(mBean)
                    } else if (mBean?.responseCode == UNAUTHORISED) {
                        data.value = UnAuthorisedRequest<String>(mBean.responseMessage!!)
                    } else {
                        data.value = Resource.Error<String>(mBean?.responseMessage!!)
                    }
                }
            }

            override fun onFailure(call: Call<OrderListResponse>, t: Throwable) {
                t.printStackTrace()
                data.value = Loading<Boolean>(false)
                data.value = Resource.Error<String>(SERVER_ERROR)
            }
        })
        return data
    }
}