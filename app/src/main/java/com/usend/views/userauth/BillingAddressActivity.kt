package com.usend.views.userauth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.base.network.model.*
import com.hbb20.CountryCodePicker
import com.usend.R
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivityBillingAddressBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.models.UserModel
import com.usend.utils.*
import com.usend.utils.PreferenceHelper.set
import com.usend.viewmodels.BillingAddressViewModel
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlin.reflect.KClass

class BillingAddressActivity(
    override val modelClass: KClass<BillingAddressViewModel> = BillingAddressViewModel::class,
    override val layoutId: Int = R.layout.activity_billing_address
) : ViewModelActivity<ActivityBillingAddressBinding, BillingAddressViewModel>() {

    private val TAG = BillingAddressActivity::class.java.simpleName
    private var user: UserModel? = null
    private var ccp: CountryCodePicker? = null

    private var countryList = arrayListOf<CountryList>()
    private var stateList = arrayListOf<StateList>()

    override fun getDataFromIntent() {
        super.getDataFromIntent()

        if (intent.hasExtra(USER_DATA)) {
            user = intent.getParcelableExtra(USER_DATA)
            viewModel.user = user!!
        }
    }

    override fun initView() {
        super.initView()

        ccp = CountryCodePicker(this)

        viewModel.getCountryList()
        viewModel.getStateList()

        binding.viewModel = viewModel
        binding.billingActivity = this
        initToolbar(toolbar = toolbar)

        /* if (BuildConfig.DEBUG) {
             viewModel.addressLine1.value = "1001, Abhishree Adroit"
             viewModel.addressLine2.value = "Mansi Circle, Oppo. Gwaliya Sweets"
             viewModel.city.value = "Ahmedabad"
             viewModel.country.value = "India"
             viewModel.zipcode.value = "360009"
             viewModel.state.value = "Gujarat"
             viewModel.countryCode.value = "+91"
             viewModel.countryCodeId.value = 240
             viewModel.phoneNumber.value = "95582560982"
         }*/

    }



    fun onNextClick() {
        viewModel.next()
    }

    fun onCountryClick() {

        val arrayList = arrayListOf<String>()

        countryList.forEachIndexed { _, countryList ->

            arrayList.add(countryList.name.nullSafe())
        }

        val intent = Intent(this, RegionSelectionActivity::class.java)
            .putExtra(EXTRA_DATA, CODE_COUNTRY)
            .putExtra(TITLE, resources.getString(R.string.lbl_country))
            .putStringArrayListExtra(LIST, arrayList)
        startActivityForResult(intent, CODE_COUNTRY)
    }




    fun openCountryCodePicker() {
        ccp?.launchCountrySelectionDialog()

        ccp?.setOnCountryChangeListener {
            viewModel.countryCode.value = ccp?.selectedCountryCodeWithPlus
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(TAG, "Message: ${response.data}")
                showToast(response.message.toString())
            }
            is Resource.Success<*> -> {

                response.data as SignUpReponse
                PreferenceHelper.saveObject(USER_DATA, response.data.responseData?.user)
                prefs[AUTH_KEY] = (response.data).responseData?.user?.authenticationToken
                OtpVerificationActivity.newIntent(
                    this, Intent(
                        this,
                        OtpVerificationActivity::class.java
                    ).putExtra(USER_DATA, user)
                )
            }
            is Resource.GetCountryListSucess<*> -> {

                response.data as GetCountryResponse

                countryList.addAll(response.data.responseData?.country!!)

            }
            is Resource.GetStateListSucess<*> -> {

                response.data as GetUSAStateList

                stateList.addAll(response.data.responseData?.usaStateList!!)

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
                // use if you wanna show dialog or snackbar
                CommonUtils.showOkDialog(this, message = resources.getString(response.id!!))
            }
            is Resource.ValidationError<*> -> {
                showToast(resources.getString(response.id.nullSafe()))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val selectedItem = data?.getStringExtra(SELECTED_DATA)

            when (requestCode) {
                CODE_COUNTRY -> {

                    binding.edtCountry.setText(selectedItem)

                    val country = countryList.find { it.name == selectedItem }
                    val countryCode = country?.phoneCode

                    binding.edtCountryCode.setText(countryCode)
                    viewModel.countryCodeId.value = country?.id!!

                    if (country.id == UNITED_STATES_CODE && stateList.size > 0) {

                        binding.edtState.setText("")

                        binding.edtState.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null,
                            ContextCompat.getDrawable(this, R.drawable.ic_drop_down_arrow),
                            null
                        )

                        binding.edtState.isFocusable = false
                        binding.edtState.hint = resources.getString(R.string.lbl_select)

                        binding.edtState.setOnClickListener {

                            val arrayList = arrayListOf<String>()

                            stateList.forEachIndexed { _, countryList ->

                                arrayList.add(countryList.name.nullSafe())
                            }

                            val intent = Intent(this, RegionSelectionActivity::class.java)
                                .putExtra(EXTRA_DATA, CODE_STATE)
                                .putExtra(TITLE, resources.getString(R.string.lbl_state))
                                .putStringArrayListExtra(LIST, arrayList)
                            startActivityForResult(intent, CODE_STATE)
                        }
                    } else {
                        binding.edtState.setText("")
                        binding.edtState.hint = resources.getString(R.string.lbl_enter_state)
                        binding.edtState.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null,
                            null,
                            null
                        )
                        binding.edtState.isFocusable = true
                        binding.edtState.isFocusableInTouchMode = true

                        binding.edtState.setOnClickListener(null)
                    }
                }
                CODE_STATE -> {
                    binding.edtState.setText(selectedItem)
                }
                CODE_CITY -> {
                    binding.edtCity.setText(selectedItem)
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }
}