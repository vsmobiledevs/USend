package com.usend.views.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.base.network.model.GetOrderResponse
import com.base.network.model.OrderPackageDetails
import com.usend.R
import com.usend.adapter.PackageListItemAdapter
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivityOrderDetailsBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.utils.*
import com.usend.viewmodels.OrderViewModel
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlin.reflect.KClass

class OrderDetailsActivity(
    override val modelClass: KClass<OrderViewModel> = OrderViewModel::class,
    override val layoutId: Int = R.layout.activity_order_details
) : ViewModelActivity<ActivityOrderDetailsBinding, OrderViewModel>() {

    private var orderId: Int? = null
    private var arrayListPackages = arrayListOf<OrderPackageDetails>()

    private var getOrderResponse: GetOrderResponse? = null

    override fun getDataFromIntent() {
        super.getDataFromIntent()

        if (intent.hasExtra(ORDER_ID)) {
            orderId = intent.getIntExtra(ORDER_ID, 0)
        }
    }

    override fun initView() {
        super.initView()

        initToolbar(toolbar = toolbar, title = resources.getString(R.string.lbl_order_details))

        binding.txtTrackingNumber.paintFlags =
            binding.txtTrackingNumber.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        viewModel.getOrderDetail(orderId.nullSafe())

        binding.rvPackageDetails.addItemDecoration(
            ItemOffsetDecoration(
                resources.getDimensionPixelOffset(R.dimen.dimen_1dp),
                "top",
                pos = 0
            )
        )

        binding.adapter = PackageListItemAdapter(arrayListPackages) {

        }
    }

    override fun initControls() {
        super.initControls()

        binding.txtTrackingNumber.setOnClickListener {

            var url = ""

            when (getOrderResponse?.responseData?.orderDetails?.carrierUsed) {
                "fedex" -> {
                    url =
                        "https://www.fedex.com/apps/fedextrack/?action=track&language=english&tracknumbers=${getOrderResponse?.responseData?.orderDetails?.trackingNumber}"
                }
                "DHL" -> {
                    url =
                        "https://www.dhl.com/en/express/tracking.html?AWB=${getOrderResponse?.responseData?.orderDetails?.trackingNumber}&brand=DHL"
                }
                "UPS" -> {
                    url =
                        "https://wwwapps.ups.com/WebTracking/processRequest?HTMLVersion=5.0&Requester=NES&AgreeToTermsAndConditions=yes&loc=en_US&tracknum=${getOrderResponse?.responseData?.orderDetails?.trackingNumber}"
                }
                "USPS" -> {
                    url =
                        "https://tools.usps.com/go/TrackConfirmAction.action?tLabels=${getOrderResponse?.responseData?.orderDetails?.trackingNumber}?"
                }
            }

            if (url.isNotEmpty()) {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
                startActivity(browserIntent)
            }
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {
                JLog.e(Companion.TAG, "Message: ${response.data}")
                showToast(response.message.toString())
            }
            is Resource.Success<*> -> {
                response.data as GetOrderResponse
                if ((response.data.responseCode ?: 0) == SUCCESS) {

                    getOrderResponse = response.data
                    binding.txtOrderNumber.text =
                        response.data.responseData?.orderDetails?.orderNumber
                    binding.txtTrackingNumber.text =
                        response.data.responseData?.orderDetails?.trackingNumber
                    binding.txtCarrierUsed.text =
                        response.data.responseData?.orderDetails?.carrierUsed
                    binding.txtShippingCost.text = CommonUtils.convertToPriceFormat(
                        response.data.responseData?.orderDetails?.shippingCost?.toDouble() ?: 0.0
                    )
                    binding.txtProcessedOn.text = DateTimeUtil.getConvertedTime(
                        DATEFORMAT_yyyy_MM_dd,
                        DATEFORMAT_dd_MMM_yyyy,
                        response.data.responseData?.orderDetails?.processedOn.toString()
                    )

                    binding.txtSpecialNotes.text =
                        if (response.data.responseData?.orderDetails?.notes.isNullOrEmpty()) "-" else response.data.responseData?.orderDetails?.notes.toString()
                            .trim()

                    if (response.data.responseData?.deliveryAddress?.countryCodeId != 198) {
                        binding.llCustoms.visibility = View.VISIBLE
                        if (!response.data.responseData?.orderDetails?.declaredValue.isNullOrEmpty()) {
                            binding.txtDeclaredValues.text = CommonUtils.convertToPriceFormat(
                                response.data.responseData?.orderDetails?.declaredValue?.toDouble()
                                    ?: 0.0
                            )
                        } else {
                            binding.llDeclaredValue.visibility = View.GONE
                        }
                        if (!response.data.responseData?.orderDetails?.packageContent.isNullOrEmpty()) {
                            binding.txtContentofPackage.text =
                                response.data.responseData?.orderDetails?.packageContent.toString()
                                    .trim()
                        } else {
                            binding.llDescOfItem.visibility = View.GONE
                        }

                    }

                    //binding.txtEstDeliveryDate.text = response.data.responseData?.orderDetails?.estimatedDeliveryDate

                    when (response.data.responseData?.orderDetails?.orderStatus) {
                        PENDING, AWAITING_SHIPMENT -> {
                            binding.txtStatus.text = resources.getString(R.string.lbl_processing)
                            binding.txtStatus.setTextColor(
                                ContextCompat.getColor(
                                    this,
                                    R.color.colorFF9500
                                )
                            )
                        }
                        else -> {
                            binding.txtStatus.text = resources.getString(R.string.lbl_shipped)
                            binding.txtStatus.setTextColor(
                                ContextCompat.getColor(
                                    this,
                                    R.color.color5A9E41
                                )
                            )
                        }
                    }

                    binding.txtServiceType.text =
                        response.data.responseData?.orderDetails?.serviceName

                    /*if(response.data.responseData?.deliveryAddress?.street2.isNullOrEmpty())
                {
                    binding.txtDeliveryAddress.text = response.data?.responseData?.deliveryAddress?.street1 + ",\n" +
                            response.data.responseData?.deliveryAddress?.city + ", " +
                            response.data.responseData?.deliveryAddress?.state + if(response.data.responseData?.deliveryAddress?.state.isNullOrEmpty()) "" else ", " +
                            response.data.responseData?.deliveryAddress?.country + " - " +
                            response.data.responseData?.deliveryAddress?.postalCode
                }
                else
                {
                    binding.txtDeliveryAddress.text = response.data?.responseData?.deliveryAddress?.street1 + ",\n" +
                            response.data?.responseData?.deliveryAddress?.street2 + ",\n" +
                            response.data?.responseData?.deliveryAddress?.city + ", " +
                            response.data?.responseData?.deliveryAddress?.state + if(response.data.responseData?.deliveryAddress?.state.isNullOrEmpty()) "" else ", " +
                            response.data?.responseData?.deliveryAddress?.country + " - " +
                            response.data?.responseData?.deliveryAddress?.postalCode
                }*/

                    var address: String

                    address = response.data.responseData?.deliveryAddress?.street1 + ",\n"

                    if (response.data.responseData?.deliveryAddress?.street2!!.isNotEmpty()) {
                        address =
                            address + response.data.responseData?.deliveryAddress?.street2 + ",\n"
                    }
                    if (response.data.responseData?.deliveryAddress?.city!!.isNotEmpty()) {
                        address =
                            address + response.data.responseData?.deliveryAddress?.city + ", "
                    }
                    if (response.data.responseData?.deliveryAddress?.state!!.isNotEmpty()) {
                        address =
                            address + response.data.responseData?.deliveryAddress?.state + ", "
                    }
                    if (response.data.responseData?.deliveryAddress?.country!!.isNotEmpty()) {
                        address += response.data.responseData?.deliveryAddress?.country
                    }
                    if (response.data.responseData?.deliveryAddress?.postalCode!!.isNotEmpty()) {
                        address =
                            address + " - " + response.data.responseData?.deliveryAddress?.postalCode
                    }

                    binding.txtDeliveryAddress.text = address

                    binding.txtCarrierFees.text = CommonUtils.convertToPriceFormat(
                        response.data.responseData?.invoiceDetails?.carrierFees?.toDouble() ?: 0.0
                    )
                    binding.txtConsolidatedFees.text = CommonUtils.convertToPriceFormat(
                        response.data.responseData?.invoiceDetails?.consolidatedFees?.toDouble() ?: 0.0
                    )
                    //binding.txtProcessFees.text =  "$" + response.data?.responseData?.invoiceDetails?.processFees.toString()
                    binding.txtAdditionalFees.text = CommonUtils.convertToPriceFormat(
                        response.data.responseData?.invoiceDetails?.additionalFees?.toDouble()
                            ?: 0.0
                    )
                    binding.txtTotalCharges.text = CommonUtils.convertToPriceFormat(
                        response.data.responseData?.invoiceDetails?.totalCharges?.toDouble() ?: 0.0
                    )

                    binding.txtDimensions.text =
                        response.data.responseData?.packageDetails!![0].dimensions + " " + response.data.responseData?.packageDetails!![0].dimensionsUnit
                    binding.txtWeight.text = String.format(
                        "%.2f",
                        response.data.responseData?.packageDetails!![0].weight!!.toDouble()
                    ) + " " + response.data.responseData?.packageDetails!![0].weightUnit

                    arrayListPackages.clear()
                    arrayListPackages.addAll(response.data.responseData?.packageDetails!!)
                    binding.adapter?.notifyDataSetChanged()
                } else {
                    showToast("Error" + response.data.responseMessage)
                }
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

        private val TAG = OrderDetailsActivity::class.java.simpleName
    }
}