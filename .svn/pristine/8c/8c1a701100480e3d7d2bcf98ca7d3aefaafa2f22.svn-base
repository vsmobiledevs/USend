package com.usend.views.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.Observer
import com.base.network.model.OrderListModel
import com.base.network.model.OrderListResponse
import com.usend.R
import com.usend.adapter.OrderHistoryItemAdapter
import com.usend.base.ViewModelFragment
import com.usend.comman.Resource
import com.usend.databinding.FragmentOrderHistoryBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.utils.*
import com.usend.viewmodels.OrderViewModel

class OrderHistoryFragment(
    override val modelClass: Class<OrderViewModel> = OrderViewModel::class.java,
    override val layoutId: Int = R.layout.fragment_order_history
) : ViewModelFragment<FragmentOrderHistoryBinding, OrderViewModel>() {

    private var arrayList = arrayListOf<OrderListModel>()
    private var processingCount = 0
     private var shippedCount = 0

    override fun initControls() {

        viewModel.getOrdersList()

        binding.rvOrderHistory.addItemDecoration(
            ItemOffsetDecoration(
                resources.getDimension(R.dimen.dimen_5dp).toInt(), "top"
            )
        )

        binding.adapter = OrderHistoryItemAdapter(arrayList) {
            OrderDetailsActivity.newIntent(
                requireActivity(), Intent(requireActivity(), OrderDetailsActivity::class.java)
                    .putExtra(ORDER_ID, arrayList[it].orderDetails?.id)
            )
        }

        binding.swipeOrderHistory.setOnRefreshListener {
            binding.swipeOrderHistory.isRefreshing = false
            viewModel.getOrdersList()
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(TAG, "Message: ${response.data}")
                showToast(requireActivity(), response.message.toString())
            }
            is Resource.Success<*> -> {

                response.data as OrderListResponse

                binding.txtTotalPackages.text =
                    response.data.responseData?.processedCount?.processedCount.nullSafe().toString()
                arrayList.clear()
                processingCount = 0
                shippedCount = 0
                response.data.responseData?.orders?.forEach {
                    val status = it.orderDetails?.orderStatus ?: ""
                    if (status == AWAITING_SHIPMENT || status == PENDING) {
                        processingCount++
                    } else if (status == SHIPPED) {
                        shippedCount++
                    }
                    arrayList.add(it)
                }

                binding.txtTotalPackages.text = processingCount.toString()
                binding.txtTotalShippedPackages.text = shippedCount.toString()
                binding.adapter?.notifyDataSetChanged()

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
                CommonUtils.showOkDialog(requireActivity(), message = resources.getString(response.id!!))
            }
            is Resource.ItemsNotFound<*> -> {
                binding.isEmpty = true
            }
            is Resource.ValidationError<*> -> {
                showToast(requireActivity(), resources.getString(response.id.nullSafe()))
            }
            is Resource.UnAuthorisedRequest<*> -> {
                showTokenExpiredDialog(resources.getString(R.string.lbl_session_expired_msg))
            }
        }
    }

    companion object {
        private val TAG = OrderHistoryFragment::class.java.simpleName
    }

}