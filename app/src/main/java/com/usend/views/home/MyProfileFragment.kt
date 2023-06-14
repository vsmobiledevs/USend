package com.usend.views.home

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.CompoundButton
import android.widget.TextView
import androidx.lifecycle.Observer
import com.base.network.model.SignUpReponse
import com.base.network.model.User
import com.usend.R
import com.usend.base.BaseActivity
import com.usend.base.ViewModelFragment
import com.usend.comman.OnDataPass
import com.usend.comman.Resource
import com.usend.databinding.FragmentMyProfileBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.utils.*
import com.usend.utils.CommonUtils.showAffirmationDialog
import com.usend.utils.PreferenceHelper.set
import com.usend.viewmodels.MyProfileViewModel
import com.usend.views.ConciergeForGuestActivity
import com.usend.views.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus


@Suppress("DEPRECATION")
class MyProfileFragment(
    override val modelClass: Class<MyProfileViewModel> = MyProfileViewModel::class.java,
    override val layoutId: Int = R.layout.fragment_my_profile
) : ViewModelFragment<FragmentMyProfileBinding, MyProfileViewModel>(),
    CompoundButton.OnCheckedChangeListener {
    private var user: User? = null
    private var onDataPass: OnDataPass? = null
    private var isAutoFillDetailsAvailable = false
    private val ENBALE_AUTOSHIPMENT_RESPONSE = 1233

    @SuppressLint("SetTextI18n")
    override fun initControls() {
        user = PreferenceHelper.getUserObject()
        viewModel.getProfileData()
        binding.txtName.text = user?.firstName + " " + user?.lastName
        binding.viewModel = viewModel
        binding.switchAutoShip.setOnCheckedChangeListener(this@MyProfileFragment)
        binding.llAutoShip.setOnClickListener {

        }

        binding.llEditProfile.setOnClickListener {
            EditProfileActivity.newIntent(
                requireActivity(),
                Intent(requireActivity(), EditProfileActivity::class.java)
            )
        }

        binding.llChangePassword.setOnClickListener {
            ChangePasswordActivity.newIntent(
                requireActivity(),
                Intent(requireActivity(), ChangePasswordActivity::class.java)
            )
        }

        binding.llContactUs.setOnClickListener {

            ContactUsActivity.newIntent(
                requireActivity(),
                Intent(requireActivity(), ContactUsActivity::class.java)
            )
        }

        binding.llProhibitedItems.setOnClickListener {

            ProhibitedItemsActivity.newIntent(
                requireActivity(),
                Intent(requireActivity(), ProhibitedItemsActivity::class.java)
            )
            /*val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(USPS_DOWNLOAD)
            )
            startActivity(browserIntent)*/
        }

        binding.llUSPSVerification.setOnClickListener {

            if (checkPremiumStatus()) when (user?.uspsStatus) {

                "" -> {
                    val intent = Intent(requireActivity(), USPSVerificationActivity::class.java)
                    startActivityForResult(intent, REQ_USPS_VERIFICATION)
                }
                NOT_APPLIED -> {
                    val intent = Intent(requireActivity(), USPSVerificationActivity::class.java)
                    startActivityForResult(intent, REQ_USPS_VERIFICATION)
                }
                else -> {
                    val intent =
                        Intent(requireActivity(), VerificationStatusActivity::class.java)
                            .putExtra(EXTRA_DATA, user?.uspsStatus)
                    startActivityForResult(intent, REQ_UPDATE_USPS_VERIFICATION)
                }
            }
            //USPSVerificationActivity.newIntent(requireActivity(), Intent(requireActivity(), USPSVerificationActivity::class.java))
        }

        binding.llMembership.setOnClickListener {
            if (user?.isSubscription!!) {
                (activity as MainActivity).bottomTab.getTabAt(3)?.select()
               onDataPass?.dataPass(PREMIMUM)

              /*  val bundle = Bundle()
                bundle.putString(IS_SUBSCRIPTION, user?.webSubscriptionId)
                val mFragment = ConciergeFragmentForSubscription()
                mFragment.arguments = bundle
                val ft : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                ft.replace(R.id.main_frame,mFragment)
                ft.commit()*/
            } else {
                (activity as MainActivity).bottomTab.getTabAt(3)?.select()
            }
        }

        binding.llShippingAddress.setOnClickListener {
            ShippingAddressActivity.newIntent(
                requireActivity(),
                Intent(activity, ShippingAddressActivity::class.java)
            )
        }

        binding.llPaymentMethods.setOnClickListener {
            SavedCardsActivity.newIntent(
                requireActivity(),
                Intent(requireActivity(), SavedCardsActivity::class.java)
            )
        }

        binding.llTandC.setOnClickListener {
            WebViewActivity.newIntent(
                requireActivity(), Intent(requireActivity(), WebViewActivity::class.java)
                    .putExtra(TITLE, resources.getString(R.string.lbl_t_and_c_))
                    .putExtra(SLUG, "terms_and_conditions")
            )

        }

        binding.llUnitSystem.setOnClickListener {
            viewModel.changeUnitSystem()
        }

        binding.llLogout.setOnClickListener {

            requireActivity().showAffirmationDialog(
                title = resources.getString(R.string.msg_logout),
                btnText = resources.getString(R.string.lbl_yes),
                btnNegativeText = resources.getString(R.string.lbl_no),
                isCancelable = true
            ) {
                viewModel.logout()
            }
        }
    }

    private fun checkPremiumStatus(): Boolean {
        if (user?.isSubscription!!) {
            return true
        } else {

            ConciergeForGuestActivity.newIntent(
                requireActivity(), Intent(requireActivity(), ConciergeForGuestActivity::class.java)
            )
        }
        return false
    }

    private fun updateAutoShipSwitch(status: Boolean) {
        binding.switchAutoShip.setOnCheckedChangeListener(null)
        binding.switchAutoShip.isChecked = status
        binding.switchAutoShip.setOnCheckedChangeListener(this@MyProfileFragment)
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(TAG, "Message: ${response.data}")
                showToast(requireActivity(), response.message.toString())
            }
            is Resource.SuccessWithData<*> -> {
                hideProgressDialog()
                if (response.data is SignUpReponse) {
                    response.data.responseMessage?.let {
                       // showToast(requireActivity(), it)
                       /* requireActivity().showAffirmationDialog(title = resources.getString(R.string.auto_shipment_off_msg),
                            btnText = resources.getString(R.string.lbl_proceed),
                            btnNegativeText = resources.getString(R.string.lbl_no))
                        {
//                            AutoShipActivity.newIntent(
//                                requireActivity(), Intent(requireActivity(), AutoShipActivity::class.java))
                        }*/

                    }
                }
                if (response.model != null && response.model == ENBALE_AUTOSHIPMENT_RESPONSE) {

                    AutoShipActivity.newIntent(
                        requireActivity(), Intent(requireActivity(), AutoShipActivity::class.java))


                }
               else if (response.model == null) {
                    showDialog(resources.getString(R.string.auto_shipment_off_msg))
                }
            }
            is Resource.Success<*> -> {

                response.data as SignUpReponse
                user = response.data.responseData?.user
                PreferenceHelper.saveObject(USER_DATA, response.data.responseData?.user)

                binding.txtMailBoxNo.text = response.data.responseData?.user?.hostAddress

                if (user?.isSubscription!!) {
                    binding.txtMembershipStatus.text = resources.getString(R.string.lbl_premium)
                } else {
                    binding.txtMembershipStatus.text = resources.getString(R.string.lbl_free)
                }

                viewModel.unitSystem.value = response.data.responseData?.user?.unitSystem


                /*when (response.data.responseData?.user?.uspsStatus) {
                    NOT_APPLIED -> {
                        binding.txtUSPSStatus.text =
                            resources.getString(R.string.lbl_usps_verification) + " (Not Applied)"

                        binding.txtStatus.text = "Not Applied"
                    }
                    VERIFICATION_PENDING -> {
                        binding.txtUSPSStatus.text =
                            resources.getString(R.string.lbl_usps_verification) + " (Pending)"

                        binding.txtStatus.text = "Pending"
                    }
                    VERIFICATION_REJECTED -> {
                        binding.txtUSPSStatus.text =
                            resources.getString(R.string.lbl_usps_verification) + " (Rejected)"

                        binding.txtStatus.text = "Rejected"
                    }
                    VERIFICATION_COMPLETED -> {
                        binding.txtUSPSStatus.text =
                            resources.getString(R.string.lbl_usps_verification) + " (Completed)"

                        binding.txtStatus.text = "Completed"
                    }
                }*/
                isAutoFillDetailsAvailable = (user?.autoShipmentCount ?: 0) == 1
                updateAutoShipSwitch(user?.isAutoShip ?: false)
            }
            is Resource.ChangeUnitSuccess<*> -> {

                when (viewModel.unitSystem.value) {
                    "kg/cm" -> {
                        viewModel.unitSystem.value = "lbs/inch"
                    }
                    "lbs/inch" -> {
                        viewModel.unitSystem.value = "kg/cm"
                    }
                }
            }
            is Resource.LogoutSuccess<*> -> {

                prefs[IS_LOGIN] = false
                MainActivity.newIntent(
                    requireActivity(), Intent(requireActivity(), MainActivity::class.java)
                        .putExtra(FROM, FROM_LOGOUT)
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
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
                // use if you wanna show dialog or snackbar
                CommonUtils.showOkDialog(
                    requireActivity(),
                    message = resources.getString(response.id!!)
                )
            }
            is Resource.UnAuthorisedRequest<*> -> {
                showTokenExpiredDialog(resources.getString(R.string.lbl_session_expired_msg))
            }
            is Resource.ValidationError<*> -> {
                showToast(requireActivity(), resources.getString(response.id.nullSafe()))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        EventBus.getDefault()
            .unregister(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onDataPass = context as OnDataPass
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_USPS_VERIFICATION) {
                viewModel.getProfileData()
            } else if (requestCode == REQ_UPDATE_USPS_VERIFICATION) {
                val intent = Intent(requireActivity(), USPSVerificationActivity::class.java)
                startActivityForResult(intent, REQ_USPS_VERIFICATION)
            }
        }
    }
    private fun showDialog(title: String) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_common)
        val body = dialog.findViewById(R.id.txtMsg) as TextView
        body.text = title
        val yesBtn = dialog.findViewById(R.id.btnPositive) as Button
        val noBtn = dialog.findViewById(R.id.btnNegative) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener {
            binding.switchAutoShip.isChecked=true
            dialog.dismiss()
        }
        dialog.show()

    }

    override fun onCheckedChanged(p0: CompoundButton?, checked: Boolean) {
        if (checkPremiumStatus()) {
            if (!isAutoFillDetailsAvailable && checked) {
                Log.e("tag","checked")
                updateAutoShipSwitch(false)
                val intent = Intent(requireActivity(), ShipToAddressActivity::class.java)
                intent.putExtra(FROM, FROM_AUTO_SHIPMENT)
                (requireActivity() as BaseActivity).startActivityForResult(intent) { _ ->
                    updateAutoShipSwitch(true)
                }
            } else {
                viewModel.updateAutoShipToggle(
                    checked,
                    if (checked) ENBALE_AUTOSHIPMENT_RESPONSE else null
                )
            }
        } else {
            updateAutoShipSwitch(false)
        }
    }

    companion object {
        private val TAG = MyProfileFragment::class.java.simpleName
    }
}