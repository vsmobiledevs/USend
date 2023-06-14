package com.usend.views.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.base.network.model.ConciergeRequestResponseData
import com.usend.R
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivityConciergeItemDetailBinding
import com.usend.extensions.loadImageUrl
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.utils.*
import com.usend.viewmodels.ConciergeViewModel
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlin.reflect.KClass

class ConciergeItemDetailActivity(
    override val modelClass: KClass<ConciergeViewModel> = ConciergeViewModel::class,
    override val layoutId: Int = R.layout.activity_concierge_item_detail
) : ViewModelActivity<ActivityConciergeItemDetailBinding,ConciergeViewModel>() {

    private var conciergeRequestResponseData = ConciergeRequestResponseData()

    override fun getDataFromIntent() {
        super.getDataFromIntent()

        if(intent.hasExtra(EXTRA_DATA))
        {
            conciergeRequestResponseData = intent.getParcelableExtra(EXTRA_DATA)!!
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()

        initToolbar(toolbar = toolbar, title = resources.getString(R.string.lbl_details))

        binding.txtWebSite.paintFlags = binding.txtWebSite.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        binding.txtProductName.text = conciergeRequestResponseData.productName
        binding.txtWebSite.text = conciergeRequestResponseData.websiteUrl
        binding.txtReqOn.text = conciergeRequestResponseData.createdDate
        binding.txtCost.text = CommonUtils.convertToPriceFormat(conciergeRequestResponseData.price?.toDouble()?:0.0)
        binding.txtPaidAmount.text = CommonUtils.convertToPriceFormat(conciergeRequestResponseData.payableAmount?.toDouble()?:0.0)
        binding.txtDescSize.text = conciergeRequestResponseData.description
        if(conciergeRequestResponseData.paymentReceivedNote.isNullOrEmpty()){
            binding.txtNote.visibility = View.GONE
            binding.lblNote.visibility = View.GONE
        }else{
            binding.lblNote.visibility = View.VISIBLE
            binding.txtNote.visibility = View.VISIBLE
            binding.txtNote.text = conciergeRequestResponseData.paymentReceivedNote
        }
        binding.txtStatus.text = conciergeRequestResponseData.status
        binding.txtTransactionDate.text = if(conciergeRequestResponseData.transactionDate.isNullOrEmpty()) "-" else DateTimeUtil.getConvertedTime(DATEFORMAT_yyyy_MM_dd_HH_mm_ss, DATEFORMAT_dd_MMM_yyyy, conciergeRequestResponseData.transactionDate.nullSafe())

        binding.imgConciergeItem.loadImageUrl(this, conciergeRequestResponseData.productImage!!, cornerRadius = CommonUtils.dpToPx(this, 10),
            placeholder = R.drawable.ic_package_place_holder, errorPlaceholder = R.drawable.ic_package_place_holder){

        }

        if(!conciergeRequestResponseData.isPayment!! && conciergeRequestResponseData.payableAmount!!.toDouble() != 0.0)
        {
            binding.btnPay.visibility = View.VISIBLE
            binding.btnPay.text = resources.getString(R.string.lbl_pay) + " " + CommonUtils.convertToPriceFormat(conciergeRequestResponseData.payableAmount?.toDouble()?:0.0)
        }


        when (conciergeRequestResponseData.status) {
            PROCESSING -> {
                binding.txtStatus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorEEBB2E
                    )
                )
            }
            SUBMITTED -> {
                binding.txtStatus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.color154A99
                    )
                )
            }
            PURCHASED -> {
                binding.txtStatus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.color5A9E41
                    )
                )
            }
            NOT_AVAILABLE -> {
                binding.txtStatus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorE82D2D
                    )
                )
            }
            PENDING_PAYMENT -> {
                binding.txtStatus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorE82D2D
                    )
                )
            }
            else ->
            {
                binding.txtStatus.setTextColor(
                    ContextCompat.getColor(
                        this,
                        R.color.color154A99
                    )
                )
            }
        }

    }

    override fun initControls() {
        super.initControls()

        binding.txtWebSite.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(binding.txtWebSite.text.toString())
            )
            startActivity(browserIntent)
        }

        binding.btnPay.setOnClickListener {

            SavedPaymentMethodsActivity.newIntent(this, Intent(this, SavedPaymentMethodsActivity::class.java)
                .putExtra(FROM, FROM_CONCIERGE)
                .putExtra(EXTRA_DATA, conciergeRequestResponseData))
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(Companion.TAG, "Message: ${response.data}")
                showToast(response.message.toString())
            }
            is Resource.ItemsNotFound<*> -> {

            }
            is Resource.ConciergePayment<*> -> {

                CommonUtils.showOkCallBackDialog(this, message = resources.getString(R.string.msg_concierge_req_payment_done)){

                    val intent = Intent()
                    setResult(Activity.RESULT_OK, intent)
                    finish()
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
                CommonUtils.showOkDialog(this,message = resources.getString(response.id!!))
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

        private val TAG = ConciergeItemDetailActivity::class.java.simpleName
    }
}