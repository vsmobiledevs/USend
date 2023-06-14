package com.usend.views.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.base.network.model.GetAdditionalServiceChargeResponse
import com.base.network.model.ServiceChargeList
import com.base.network.model.ShippingDetailsResponse
import com.usend.R
import com.usend.adapter.ExtraServicesItemAdapter
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivityShippingDetailsBinding
import com.usend.extensions.loadImageUrl
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.utils.*
import com.usend.viewmodels.ShippingDetailViewModel
import kotlinx.android.synthetic.main.app_toolbar.*

import kotlin.reflect.KClass


class ShippingDetailsActivity(
    override val modelClass: KClass<ShippingDetailViewModel> = ShippingDetailViewModel::class,
    override val layoutId: Int = R.layout.activity_shipping_details
) : ViewModelActivity<ActivityShippingDetailsBinding, ShippingDetailViewModel>() {

    private var selectedServices = arrayListOf<Int>()
    private var arrayListAdditionalServices = arrayListOf<ServiceChargeList>()

    private var listPackageIds = arrayListOf<String>()
    private var addressId: Int? = null
    private var serviceName: String? = null
    private var serviceCode: String? = null
    private var carrierCode: String? = null
    private var shippingCost: Double? = null
    private var shippingDetails : ShippingDetailsResponse? = null
    private var additionalCharges = 0.0

    override fun getDataFromIntent() {
        super.getDataFromIntent()

        if(intent.hasExtra(ADDRESS_ID))
        {
            addressId = intent.getIntExtra(ADDRESS_ID, 0)
            listPackageIds = intent.getStringArrayListExtra(PACKAGE_IDs)!!
            serviceName = intent.getStringExtra(SERVICE_NAME)
            serviceCode = intent.getStringExtra(SERVICE_CODE)
            carrierCode = intent.getStringExtra(CARRIER_CODE)
            shippingCost = intent.getDoubleExtra(SHIPPING_COST, 0.0)
        }

    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()

        viewModel.getShippingDetails(
            addressId!!,
            listPackageIds.joinToString(separator = ","),
            serviceName!!,
            shippingCost!!,
            serviceCode!!,
            carrierCode!!
        )

        initToolbar(toolbar = toolbar, title = resources.getString(R.string.lbl_shipping_details))
        binding.activity = this

        binding.adapter = ExtraServicesItemAdapter(arrayListAdditionalServices){

            if(selectedServices.contains(arrayListAdditionalServices[it].id))
            {
                selectedServices.remove(arrayListAdditionalServices[it].id.nullSafe())
                additionalCharges -= arrayListAdditionalServices[it].price!!.toDouble()
            }
            else {
                selectedServices.add(arrayListAdditionalServices[it].id.nullSafe())
                additionalCharges += arrayListAdditionalServices[it].price!!.toDouble()
            }

            binding.txtAdditionalFees.text = "$" + String.format(
                "%.2f",
                (additionalCharges + shippingDetails?.responseData?.invoiceDetails?.additionalFees!!.toDouble())
            )
            binding.txtTotalCharges.text =  "$" + String.format(
                "%.2f",
                shippingDetails?.responseData?.invoiceDetails?.totalCharges!!.toDouble() + additionalCharges
            )
        }
    }

    fun onShippingMethodChangeClick()
    {
        onBackPressed()
    }

    fun onShippingAddressChangeClick()
    {
        val intent = Intent(this, ShipToAddressActivity::class.java)
        intent.putExtra(FROM, FROM_SHIPPINGDETAIL)
        startActivityForResult(intent, CODE_ADDRESS)
    }

    override fun initControls() {
        super.initControls()

        binding.btnNext.setOnClickListener {
            val declaredValue = binding.edtDeclaredValues.text.toString()
            val contentPackage = binding.edtContentOfPackage.text.toString()

            if(shippingDetails?.responseData?.shippingAddress?.countryCodeId != 198)
            {
                when {
                    declaredValue.isEmpty() -> showToast(resources.getString(R.string.msg_please_enter_declared_value))
                    contentPackage.isEmpty() -> showToast(resources.getString(R.string.msg_please_enter_desc_of_item))
                    else -> SavedPaymentMethodsActivity.newIntent(this, Intent(this, SavedPaymentMethodsActivity::class.java)
                        .putExtra(FROM, FROM_SHIPPINGDETAIL)
                        .putExtra(ADDRESS_ID,addressId)
                        .putExtra(ADDITIONAL_CHARGES, additionalCharges)
                        .putStringArrayListExtra(PACKAGE_IDs, listPackageIds)
                        .putExtra(SHIPPING_COST, shippingCost)
                        .putExtra(SHIPPING_DETAILS, shippingDetails)
                        .putExtra(SELETED_SERVICES, selectedServices)
                        .putExtra(NOTE, binding.edtNotes.text.toString())
                        .putExtra(DECLARED_VALUES, binding.edtDeclaredValues.text.toString())
                        .putExtra(CONTENT_OF_PACKAGE, binding.edtContentOfPackage.text.toString()))
                }
            }
            else
            {
                SavedPaymentMethodsActivity.newIntent(this, Intent(this, SavedPaymentMethodsActivity::class.java)
                    .putExtra(FROM, FROM_SHIPPINGDETAIL)
                    .putExtra(ADDRESS_ID, addressId)
                    .putStringArrayListExtra(PACKAGE_IDs, listPackageIds)
                    .putExtra(SHIPPING_COST, shippingCost)
                    .putExtra(ADDITIONAL_CHARGES,  additionalCharges)
                    .putExtra(SHIPPING_DETAILS, shippingDetails)
                    .putExtra(SELETED_SERVICES, selectedServices)
                    .putExtra(NOTE, binding.edtNotes.text.toString())
                    .putExtra(DECLARED_VALUES, binding.edtDeclaredValues.text.toString())
                    .putExtra(CONTENT_OF_PACKAGE, binding.edtContentOfPackage.text.toString()))
            }
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(Companion.TAG, "Message: ${response.data}")
                showToast(response.message.toString())
            }
            is Resource.AdditionalServicesSucess<*> -> {

                response.data as GetAdditionalServiceChargeResponse

                arrayListAdditionalServices.addAll(response.data.responseData?.additionalServices!!)

                binding.adapter?.notifyDataSetChanged()
            }
            is Resource.Success<*> -> {

                response.data as ShippingDetailsResponse
                viewModel.getAdditionalServices()
                shippingDetails = response.data

                if(shippingDetails?.responseData?.shippingAddress?.countryCodeId != 198)
                {
                    binding.llCustoms.visibility = View.VISIBLE
                }

                binding.txtConsolidatedFees.text = CommonUtils.convertToPriceFormat(
                    shippingDetails?.responseData?.invoiceDetails?.consolidatedFees?.toDouble() ?: 0.0
                )
                /*if (response.data?.responseData?.shippingAddress?.street2.isNullOrEmpty()) {
                    binding.txtAddress.text =
                        response.data?.responseData?.shippingAddress?.street1 + ",\n" +
                                response.data?.responseData?.shippingAddress?.city + ", " +
                                response.data?.responseData?.shippingAddress?.state + ", " +
                                response.data?.responseData?.shippingAddress?.country + " - " +
                                response.data?.responseData?.shippingAddress?.postalCode

                } else {
                    binding.txtAddress.text =
                        response.data?.responseData?.shippingAddress?.street1 + ",\n" +
                                response.data?.responseData?.shippingAddress?.street2 + ",\n" +
                                response.data?.responseData?.shippingAddress?.city + ", " +
                                response.data?.responseData?.shippingAddress?.state + ", " +
                                response.data?.responseData?.shippingAddress?.country + " - " +
                                response.data?.responseData?.shippingAddress?.postalCode
                }*/

                var address: String

                address = response.data.responseData?.shippingAddress?.street1 + ",\n"

                if(response.data.responseData?.shippingAddress?.street2!!.isNotEmpty())
                {
                    address = address + response.data.responseData?.shippingAddress?.street2 + ",\n"
                }
                if(response.data.responseData?.shippingAddress?.city!!.isNotEmpty())
                {
                    address = address + response.data.responseData?.shippingAddress?.city + ", "
                }
                if(response.data.responseData?.shippingAddress?.state!!.isNotEmpty())
                {
                    address = address + response.data.responseData?.shippingAddress?.state + ", "
                }
                if(response.data.responseData?.shippingAddress?.country!!.isNotEmpty())
                {
                    address += response.data.responseData?.shippingAddress?.country
                }
                if(response.data.responseData?.shippingAddress?.postalCode!!.isNotEmpty())
                {
                    address = address + " - " + response.data.responseData?.shippingAddress?.postalCode
                }

                binding.txtAddress.text = address

                binding.txtShippingMethodName.text =
                    response.data.responseData?.shippingMethod?.serviceName
                binding.txtShippingCharges.text = "$" + String.format(
                    "%.2f",
                    response.data.responseData?.shippingMethod?.shipmentCost?.toDouble()
                )
                binding.txtAvailability.text =
                    response.data.responseData?.shippingMethod?.deliveryTime.toString()
                binding.txtCarrierFees.text = "$" + String.format(
                    "%.2f",
                    response.data.responseData?.invoiceDetails?.carrierFees?.toDouble()
                )
                /*binding.txtProcessFees.text = "$" + String.format(
                    "%.2f",
                    response.data?.responseData?.invoiceDetails?.processFees?.toDouble()
                )*/
                binding.txtAdditionalFees.text = "$" + String.format(
                    "%.2f",
                    response.data.responseData?.invoiceDetails?.additionalFees?.toDouble()
                )
                binding.txtTotalCharges.text = "$" + String.format(
                    "%.2f",
                    response.data.responseData?.invoiceDetails?.totalCharges?.toDouble()
                )

                 binding.imgShipping.loadImageUrl(this, response.data.responseData?.shippingMethod?.image!!, cornerRadius = CommonUtils.dpToPx(this, 20),
                    placeholder = R.drawable.ic_package_place_holder, errorPlaceholder = R.drawable.ic_package_place_holder){

                }

            }
            is Resource.CreateOrderSucess<*> -> {

                PaymentSuccessfulActivity.newIntent(
                    this, Intent(
                        this,
                        PaymentSuccessfulActivity::class.java
                    )
                )
                finishAffinity()
            }
            is Resource.Loading<*> -> {

                response.isLoadingShow.let {
                    if (it as Boolean) {
                        showProgressDialog(this)
                    } else {
                        hideProgressDialog()
                    }
                }
            }

            is Resource.NoInternetError<*> -> {
                CommonUtils.showOkDialog(this, message = resources.getString(response.id!!))
            }
            is Resource.ValidationError<*> -> {
                showToast(resources.getString(response.id.nullSafe()))
            }
            is Resource.UnAuthorisedRequest<*> -> {
                showTokenExpiredDialog(resources.getString(R.string.lbl_session_expired_msg))
            }
        }
    }



    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }

        private val TAG = ShippingDetailsActivity::class.java.simpleName
    }
}