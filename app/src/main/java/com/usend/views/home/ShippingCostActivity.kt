package com.usend.views.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.network.model.CarrierList
import com.base.network.model.GetRatesList
import com.base.network.model.ListshippingCalculatorRates
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.usend.R
import com.usend.adapter.ShippingMethodFilterItemAdapter
import com.usend.adapter.ShippingMethodItemAdapter
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivityShippingCostBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.models.ShippingCalculatorModel
import com.usend.utils.*
import com.usend.viewmodels.ShippingCostViewModel
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.app_toolbar.view.*
import kotlin.reflect.KClass


class ShippingCostActivity(
    override val modelClass: KClass<ShippingCostViewModel> = ShippingCostViewModel::class,
    override val layoutId: Int = R.layout.activity_shipping_cost
) : ViewModelActivity<ActivityShippingCostBinding, ShippingCostViewModel>() {
    private val TAG = ShippingCostActivity::class.java.simpleName
    private var arrayListShippingMethods = arrayListOf<GetRatesList>()
    private var carrierList = arrayListOf<CarrierList>()
    private var shippingCalModel: ShippingCalculatorModel? = null
    private var selectedFilterValue = arrayListOf<String>()
    private var carrierPosition: Int? = null
    private var position: Int=0

    override fun getDataFromIntent() {
        super.getDataFromIntent()
        if (intent.hasExtra(EXTRA_DATA)) {
            carrierList = intent.getParcelableArrayListExtra(CARRIER_LIST)!!
            shippingCalModel = intent.getParcelableExtra(SHIPPING_CAL_MODEL)
        }
    }

    override fun initView() {
        super.initView()

        initToolbar(
            toolbar = toolbar,
            title = resources.getString(R.string.lbl_shipping_cost),
            isRightTextVisible = true,
            rightButtonText = resources.getString(R.string.lbl_sort_by)
        )

        selectedFilterValue.add("")

        binding.toolbarShippingCost.txtRightButton.setOnClickListener {
            openSortByDialog()
        }

        binding.rvShippingCost.adapter = ShippingMethodItemAdapter(
            arrayListShippingMethods, carrierList.filter { it.code?.isNotBlank()!! })
        {

        }


        binding.rvShippingCostFilter.addItemDecoration(
            ItemOffsetDecoration(
                resources.getDimension(
                    R.dimen.dimen_20dp
                ).toInt(), "left"
            )
        )

        binding.isEmpty = arrayListShippingMethods.size == 0

        binding.rvShippingCostFilter.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvShippingCostFilter.adapter = ShippingMethodFilterItemAdapter(carrierList) { it ->

            selectedFilterValue.clear()
            selectedFilterValue.add(carrierList[it].code!!)
            carrierPosition = it
            if (it == 0) {
                if (arrayListShippingMethods.isEmpty()) {
                    shippingCalModel?.countryCodeId?.let { it1 ->
                        viewModel.getShippingMethods(
                            countryCodeId = it1,
                            city = shippingCalModel!!.city,
                            width = shippingCalModel!!.width.toDouble(),
                            length = shippingCalModel!!.length.toDouble(),
                            height = shippingCalModel!!.height.toDouble(),
                            weight = shippingCalModel!!.weight.toDouble(),
                            dimensonSelected = shippingCalModel!!.dimensionUnit,
                            weightSelected = shippingCalModel!!.weight_unit,
                            zipCode = shippingCalModel!!.zipcode,
                            carrierCode = selectedFilterValue.joinToString(separator = ","),
                            priceFilter = if (viewModel.selectedPriceFilter.value!! == resources.getString(
                                    R.string.lbl_high_to_low
                                )
                            ) HIGH_TO_LOW else LOW_TO_HIGH,
                            carrierList = carrierList.filter { it.code?.isNotBlank()!! }
                        )
                    }
                    //   }
                } else {
                    sortingShipMethods()
                }
            } else {
                sortingShipMethods()
            }
        }
    }


    private fun openSortByDialog() {
        val mBottomSheetDialog = BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)
        val sheetView: View =
            layoutInflater.inflate(R.layout.bottomsheet_shippingmethod_filter, null)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()

        if (viewModel.selectedPriceFilter.value!! == resources.getString(R.string.lbl_high_to_low)) {
            sheetView.findViewById<AppCompatRadioButton>(R.id.rbHighToLow).isChecked = true
        } else {
            sheetView.findViewById<AppCompatRadioButton>(R.id.rbLowToHigh).isChecked = true
        }

        val rg: RadioGroup = sheetView.findViewById(R.id.radioGroup)

        rg.setOnCheckedChangeListener { _, checkedId ->

            val rb = sheetView.findViewById<RadioButton>(checkedId)
            viewModel.selectedPriceFilter.value = rb.text.toString()
            mBottomSheetDialog.dismiss()

            sortingShipMethods()
        }

    }

    private fun sortingShipMethods() {
        val list: List<GetRatesList> =
            if (viewModel.selectedPriceFilter.value!! == resources.getString(R.string.lbl_high_to_low)) {
                if (carrierPosition == 0 || selectedFilterValue[0].isBlank()) {
                    arrayListShippingMethods
                        .sortedByDescending { it.shipmentCost }
                        .toCollection(ArrayList())
                } else {
                    arrayListShippingMethods
                        .filter { it.carrierCode == selectedFilterValue[0] }
                        .sortedByDescending { it.shipmentCost }
                        .toCollection(ArrayList())
                }
            } else {
                if (carrierPosition == 0 || selectedFilterValue[0].isBlank()) {
                    arrayListShippingMethods
                        .sortedBy { it.shipmentCost }
                        .toCollection(ArrayList())
                } else {
                    arrayListShippingMethods
                        .filter { it.carrierCode == selectedFilterValue[0] }
                        .sortedBy { it.shipmentCost }
                        .toCollection(ArrayList())
                }
            }

        binding.rvShippingCost.adapter =
            ShippingMethodItemAdapter(list as ArrayList<GetRatesList>, arrayListOf()) {}
        binding.rvShippingCost.adapter?.notifyItemRangeChanged(0, list.size)
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {
                JLog.e(TAG, "Message: ${response.data}")
                // showToast(this, response.message.toString())
                // arrayListShippingMethods.clear()
                //     binding.dynamic.removeView(carrierView[0].view)
                //     carrierView.removeAt(0)
                //  binding.rvShippingCost.adapter?.notifyDataSetChanged()

            }
            is Resource.Success<*> -> {

                response.data as ListshippingCalculatorRates
                arrayListShippingMethods.addAll(
                    response.data.responseData?.shippingCalculatorRates ?: arrayListOf()
                )
                position++
                binding.rvShippingCost.visibility = View.VISIBLE
                binding.rvShippingCost.adapter = ShippingMethodItemAdapter(
                    arrayListShippingMethods, carrierList.filter { it.code?.isNotBlank()!! }.drop(position)
                ) {}
                binding.rvShippingCost.adapter?.notifyDataSetChanged()
                binding.linhishide.visibility = View.GONE
            }
            is Resource.Loading<*> -> {

                response.isLoadingShow.let {
                    if (it as Boolean) {
                        binding.rvShippingCost.visibility = View.GONE
                        // showProgressDialog(this)
                    } else {
                        // binding.simmerLayout.visibility = View.GONE
                        binding.rvShippingCost.visibility = View.VISIBLE
                        //  binding.simmerLayout.stopShimmer()
                    }
                }
            }
            is Resource.NoInternetError<*> -> {
                CommonUtils.showOkDialog(this, message = resources.getString(response.id!!))
            }
            is Resource.ValidationError<*> -> {
                showToast(this, resources.getString(response.id.nullSafe()))
            }
            is Resource.ItemsNotFound<*> -> {
                showToast(response.message.toString())
                binding.isEmpty = true
                binding.linhishide.visibility = View.VISIBLE
                arrayListShippingMethods.clear()
                binding.rvShippingCost.adapter?.notifyDataSetChanged()
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
    }
}