package com.usend.views.home

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.android.billingclient.api.*
import com.usend.R
import com.usend.base.ViewModelFragment
import com.usend.comman.Resource
import com.usend.databinding.FragmentConciergeForGuestBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.utils.*
import com.usend.viewmodels.ConciergeViewModel
import java.util.*


class ConciergeFragmentForGuest(
    override val modelClass: Class<ConciergeViewModel> = ConciergeViewModel::class.java,
    override val layoutId: Int = R.layout.fragment_concierge_for_guest
) : ViewModelFragment<FragmentConciergeForGuestBinding, ConciergeViewModel>(),
    PurchasesUpdatedListener,
    BillingClientStateListener, ConsumeResponseListener {
    private val skuDetails = HashMap<String, SkuDetails>()
    private lateinit var billingClient: BillingClient


    override fun initControls() {

        binding.cvMain.background = ContextCompat.getDrawable(
            requireActivity(),
            R.drawable.bg_curve_border_10_radius_upgrade_to_premium
        )

        if (!prefs.getBoolean(IS_LOGIN, false)) {
            binding.btnMonthly.visibility = View.GONE
        }

        billingClient =
            BillingClient.newBuilder(requireContext()).enablePendingPurchases().setListener(this)
                .build()
        billingClient.startConnection(this)

        binding.btnMonthly.setOnClickListener {

            SavedPaymentMethodsActivity.newIntent(
                requireActivity(),
                Intent(requireActivity(), SavedPaymentMethodsActivity::class.java)
                    .putExtra(FROM, FROM_SUBSCRITION)
                    .putExtra(PLAN_ID, SUBSCRIPTION_MONTHLY)
                    .putExtra(PLAN_TYPE, MONTH_VALUE)
                    .putExtra(AMOUNT, AMOUNT_MONTHLY)
            )

            /*  if(skuDetails.size > 0) {

                  val flowParams =
                      BillingFlowParams.newBuilder().setSkuDetails(skuDetails[USEND_SUB_KEY]!!)
                          .build()

                  billingClient.launchBillingFlow(requireActivity(), flowParams)
              }*/
        }
        binding.btnYearly.setOnClickListener {
            SavedPaymentMethodsActivity.newIntent(
                requireActivity(),
                Intent(requireActivity(), SavedPaymentMethodsActivity::class.java)
                    .putExtra(FROM, FROM_SUBSCRITION)
                    .putExtra(PLAN_TYPE, YEAR_VALUE)
                    .putExtra(PLAN_ID, SUBSCRIPTION_YEARLY)
                    .putExtra(AMOUNT, AMOUNT_YEARLY)
            )
        }
    }

    override fun onBillingSetupFinished(result: BillingResult) {

        val params = SkuDetailsParams.newBuilder()
            .setSkusList(listOf(USEND_SUB_KEY))
            .setType(BillingClient.SkuType.SUBS)
            .build()

        billingClient.querySkuDetailsAsync(params) { _, skuDetailsList ->
            // Process the result.
            skuDetailsList?.forEach { item ->
                skuDetails[item.sku] = item
            }
        }


    }

    override fun onPurchasesUpdated(result: BillingResult, purchases: MutableList<Purchase>?) {
        if (result.responseCode != BillingClient.BillingResponseCode.OK || purchases == null) {
            Toast.makeText(requireContext(), result.debugMessage, Toast.LENGTH_LONG).show()
            return
        } else {
            if (!purchases[0].isAcknowledged) {
                val acknowledgePurchaseParams =

                    AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchases[0].purchaseToken)
                        .build()
                billingClient.acknowledgePurchase(
                    acknowledgePurchaseParams
                ) {

                }
            }
        }
    }

    override fun onBillingServiceDisconnected() {
        Log.d(TAG, "failed")
    }

    override fun onConsumeResponse(p0: BillingResult, p1: String) {
        Log.d(TAG, "purchase done")
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(TAG, "Message: ${response.data}")
                //showToast(requireActivity(), response.message.toString())
            }
            is Resource.ItemsNotFound<*> -> {

            }
            is Resource.Success<*> -> {

                /*  response.data as SubscriptionResponse

                  //prefs[IS_SUBSCRIPTION] = response?.data?.subscription

                  MainActivity.newIntent(
                      requireActivity(), Intent(requireActivity(), MainActivity::class.java)
                          .putExtra(FROM, FROM_CONCIERGE_PURCHASE)
                          .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                          .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                          .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                  )
                  requireActivity().finish()*/
            }
            is Resource.Loading<*> -> {

                response.isLoadingShow.let {
                    if (it as Boolean) {
                        showProgressDialog(requireActivity())
                    } else {
                        hideProgressDialog()
                    }
                }
            }
            is Resource.NoInternetError<*> -> {

                CommonUtils.showOkDialog(
                    requireActivity(),
                    message = resources.getString(response.id!!)
                )
            }
            is Resource.ValidationError<*> -> {
                showToast(requireActivity(), resources.getString(response.id.nullSafe()))
            }
            is Resource.UnAuthorisedRequest<*> -> {
                showTokenExpiredDialog(resources.getString(R.string.lbl_session_expired_msg))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        billingClient.endConnection()
    }

    companion object {
        private val TAG = ConciergeFragmentForGuest::class.java.simpleName
    }
}