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
import com.base.network.model.ListGetRatesResponse
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.usend.R
import com.usend.adapter.ShippingMethodFilterItemAdapter
import com.usend.adapter.ShippingMethodItemAdapter
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivityShippingMethodBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.utils.*
import com.usend.viewmodels.ShippingMethodViewModel
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.app_toolbar.view.*
import kotlin.reflect.KClass


class ShippingMethodActivity(
    override val modelClass: KClass<ShippingMethodViewModel> = ShippingMethodViewModel::class,
    override val layoutId: Int = R.layout.activity_shipping_method
) : ViewModelActivity<ActivityShippingMethodBinding, ShippingMethodViewModel>() {

    private var carrierList = arrayListOf<CarrierList>()
    private var arrayListShippingMethods = arrayListOf<GetRatesList>()
    private var selectedFilterValue = arrayListOf<String>()
    private var from: String? = null
    private var carrierPosition: Int? = null
    private var listPackageIds = arrayListOf<String>()
    private var addressId: Int? = null
    private var zipCode: String? = null
    private var position: Int=0


    override fun getDataFromIntent() {
        super.getDataFromIntent()
        carrierList = intent.getParcelableArrayListExtra(CARRIER_LIST)!!
        if (intent.hasExtra(FROM)) {
            from = intent.getStringExtra(FROM)
        }

        if (intent.hasExtra(PACKAGE_IDs)) {
            listPackageIds = intent.getStringArrayListExtra(PACKAGE_IDs)!!
        }

        if (intent.hasExtra(ADDRESS_ID)) {
            addressId = intent.getIntExtra(ADDRESS_ID, 0)
        }

        if (intent.hasExtra(ZIP_CODE)) {
            zipCode = intent.getStringExtra(ZIP_CODE)
        }

    }

    override fun initView() {
        super.initView()

        selectedFilterValue.add("")

        /*viewModel.getShippingMethods(
            address_id = address_id!!,
            package_ids = listPackageIds.joinToString(separator = ","),
            zip_code = zip_code.nullSafe(),
            carrier_code = selectedFilterValue.joinToString(separator = ","),
            profile_filter = if (viewModel.selectedPriceFilter.value!! == resources.getString(R.string.lbl_high_to_low)) HIGH_TO_LOW else LOW_TO_HIGH
        )*/

        //callApi()
        initToolbar(
            toolbar = toolbar,
            title = if (from == FROM_SHIP_TO_ADDRESS) resources.getString(R.string.lbl_shipping_method) else resources.getString(
                R.string.lbl_shipping_cost
            ),
            isRightTextVisible = true,
            rightButtonText = resources.getString(R.string.lbl_sort_by)
        )

        binding.rvShippingMethodFilter.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        binding.rvShippingMethodFilter.addItemDecoration(
            ItemOffsetDecoration(
                resources.getDimension(
                    R.dimen.dimen_20dp
                ).toInt(), "left"
            )
        )

        binding.rvShippingMethod.adapter = ShippingMethodItemAdapter(
            arrayListShippingMethods, carrierList.filter { it.code?.isNotBlank()!! }
        ) {

            if (from == FROM_SHIP_TO_ADDRESS) {
                ShippingDetailsActivity.newIntent(
                    this,
                    Intent(this, ShippingDetailsActivity::class.java)
                        .putStringArrayListExtra(PACKAGE_IDs, listPackageIds)
                        .putExtra(ADDRESS_ID, addressId!!)
                        .putExtra(SERVICE_NAME, arrayListShippingMethods[it].serviceName)
                        .putExtra(
                            SHIPPING_COST, arrayListShippingMethods[it].shipmentCost?.toDouble()
                        )
                        .putExtra(SERVICE_CODE, arrayListShippingMethods[it].serviceCode)
                        .putExtra(CARRIER_CODE, arrayListShippingMethods[it].carrierCode)
                )
            }
        }

        binding.rvShippingMethodFilter.adapter = ShippingMethodFilterItemAdapter(carrierList) {

            selectedFilterValue.clear()
            selectedFilterValue.add(carrierList[it].code!!)
            carrierPosition = it

            if (it == 0) {
                if (arrayListShippingMethods.isEmpty()) {
                    viewModel.getShippingMethods(
                        address_id = addressId!!,
                        package_ids = listPackageIds.joinToString(separator = ","),
                        zip_code = zipCode.nullSafe(),
                        carrier_code = selectedFilterValue.joinToString(separator = ","),
                        profile_filter = if (viewModel.selectedPriceFilter.value!! == resources.getString(
                                R.string.lbl_high_to_low
                            )
                        ) HIGH_TO_LOW else LOW_TO_HIGH,
                        carrierList = carrierList.filter { it.code?.isNotBlank()!! }
                    )
                } else {
                    sortingShipMethods()
                }
            } else {
                sortingShipMethods()
            }


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

        binding.rvShippingMethod.adapter =
            ShippingMethodItemAdapter(list as ArrayList<GetRatesList>, arrayListOf()) {}
        binding.rvShippingMethod.adapter?.notifyItemRangeChanged(0, list.size)
    }

    private fun openSortByDialog() {
        val mBottomSheetDialog =
            BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)
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
            /* viewModel.getShippingMethods(
                 address_id = address_id!!,
                 package_ids = listPackageIds.joinToString(separator = ","),
                 zip_code = zip_code.nullSafe(),
                 carrier_code = selectedFilterValue.joinToString(separator = ","),
                 profile_filter = if (viewModel.selectedPriceFilter.value!! == resources.getString(
                         R.string.lbl_high_to_low
                     )
                 ) HIGH_TO_LOW else LOW_TO_HIGH
             )
 */
        }

    }


    override fun initControls() {
        super.initControls()

        binding.toolbarShippingMethod.txtRightButton.setOnClickListener {
            openSortByDialog()
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {
                JLog.e(Companion.TAG, "Message: ${response.data}")
               // showToast(response.message.toString())
                //binding.isEmpty = true
               // arrayListShippingMethods.clear()

                //binding.rvShippingMethod.adapter?.notifyDataSetChanged()
            }
            is Resource.ItemsNotFound<*> -> {

                binding.isEmpty = true

            }
            is Resource.Success<*> -> {

                response.data as ListGetRatesResponse
                arrayListShippingMethods.addAll(response.data.responseData?.getrates?: arrayListOf())
                position++
                binding.rvShippingMethod.adapter = ShippingMethodItemAdapter(
                    arrayListShippingMethods, carrierList.filter { it.code?.isNotBlank()!! }.drop(position)
                ) {}
                binding.isEmpty = arrayListShippingMethods.size == 0
                binding.rvShippingMethod.visibility = View.VISIBLE
                binding.rvShippingMethod.adapter?.notifyDataSetChanged()

                /* if(isFirstCall)
                 {
                     isFirstCall = false
                     arrayList.add(CarrierList(code = "", name = resources.getString(R.string.lbl_all)))
                     arrayList.addAll(response.data?.responseData?.carrierList!!)
                     binding.rvShippingMethodFilter.adapter?.notifyDataSetChanged()
                 }*/

            }
            is Resource.Loading<*> -> {

                response.isLoadingShow.let {
                    if (it as Boolean) {
                        binding.rvShippingMethod.visibility = View.GONE
                        // showProgressDialog(this)
                    } else {
                        binding.rvShippingMethod.visibility = View.VISIBLE
                        if (binding.rvShippingMethod.visibility == View.VISIBLE) {
                            binding.rvShippingMethod.visibility = View.VISIBLE
                        } else {
                            binding.linhideNodata.visibility = View.VISIBLE
                        }
                    }
                }
            }
            is Resource.NoInternetError<*> -> {

                // use if you wanna show dialog or snackbar
                CommonUtils.showOkDialog(this, message = resources.getString(response.id!!))
            }
            is Resource.ValidationError<*> -> {
                showToast(resources.getString(response.id.nullSafe()))
            }
            is Resource.ItemsNotFound<*> -> {
                showToast(response.message.toString())
                binding.isEmpty = true
                arrayListShippingMethods.clear()
                binding.rvShippingMethod.adapter?.notifyDataSetChanged()
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

        private val TAG = ShippingMethodActivity::class.java.simpleName
    }
}