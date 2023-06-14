package com.usend.views.userauth

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import com.base.network.model.SignUpReponse
import com.base.network.model.User
import com.google.android.material.button.MaterialButton
import com.hbb20.CountryCodePicker
import com.usend.R
import com.usend.comman.Resource
import com.usend.base.ViewModelActivity
import com.usend.databinding.ActivityOtpVerificationBinding
import com.usend.extensions.*
import com.usend.utils.*
import com.usend.utils.PreferenceHelper.set
import com.usend.viewmodels.OtpVerificationViewModel
import com.usend.views.MainActivity
import kotlinx.android.synthetic.main.app_toolbar.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

class OtpVerificationActivity(
    override val modelClass: KClass<OtpVerificationViewModel> = OtpVerificationViewModel::class,
    override val layoutId: Int = R.layout.activity_otp_verification
) : ViewModelActivity<ActivityOtpVerificationBinding, OtpVerificationViewModel>() {
    private var ccp: CountryCodePicker? = null
    private val TAG = OtpVerificationActivity::class.java.simpleName
    private var mobileNumber : String? = ""
    private var countryCode : String? = ""
    private var countDownTimer : CountDownTimer? = null
    private var user : User? = null
    private var tempMobileNo : String? = null
    private var tempCountryCode : String? = null


    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        initToolbar(toolbar = toolbar)

        user = PreferenceHelper.getUserObject()

        countryCode = user?.phoneCode
        mobileNumber = user?.phone

        binding.viewModel = viewModel
        binding.activity = this
        ccp = CountryCodePicker(this)

        binding.txtResend.paintFlags = binding.txtResend.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.txtMobileNo.text = "$countryCode $mobileNumber"
    }

    override fun initControls() {
        super.initControls()

        displayTimer(60 * 2000)

        binding.otpView.setOtpCompletionListener {
            viewModel.otp.value = it
            viewModel.verify()
        }

        binding.txtResend.setOnClickListener {
            showToast(String.format(resources.getString(R.string.msg_otp_send_success), "${binding.txtMobileNo.text}"))
            displayTimer(60 * 2000)
            viewModel.resend()
        }

        binding.txtMobileNo.setOnClickListener {
            updateMobileNumberDailog()
        }
    }

    private fun displayTimer(milisUntilFinish : Long) {

        viewModel.isTimer.value = true

        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }

        countDownTimer = object : CountDownTimer(milisUntilFinish, 1000) { // adjust the milli seconds here

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished : Long) {
                binding.txtTimer.text = ("" + String.format(FORMAT, TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))))
            }

            override fun onFinish() {

                binding.txtTimer.invisible()
                viewModel.isTimer.value = false
                this.cancel()
            }
        }
        countDownTimer?.start()
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("SetTextI18n")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(TAG, "Message: ${response.data}")
                showToast(response.message.toString())
            }
            is Resource.Success<*> -> {

                showToast(String.format(resources.getString(R.string.msg_otp_send_success), "$mobileNumber"))

                binding.otpView.setText("")
                displayTimer(60 * 2000)
            }
            is Resource.UpdateMobileSuccess<*> -> {

                countryCode = tempCountryCode
                mobileNumber = tempMobileNo
                binding.txtMobileNo.text = "$countryCode $mobileNumber"

                showToast(String.format(resources.getString(R.string.msg_otp_send_success), "$mobileNumber"))
                binding.otpView.setText("")
                displayTimer(60 * 2000)
            }
            is Resource.VerifyOTPSuccess<*> -> {

                prefs[IS_LOGIN] = true
                response.data as SignUpReponse
                PreferenceHelper.saveObject(USER_DATA, response.data.responseData?.user)
                prefs[AUTH_KEY] = (response.data).responseData?.user?.authenticationToken
                prefs[IS_SUBSCRIPTION] = (response.data).responseData?.user?.isSubscription
                MainActivity.newIntent(this, Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
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

    @SuppressLint("SetTextI18n")
    private fun updateMobileNumberDailog() {

        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_update_mobile)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.CENTER)
        dialog.window?.setWindowAnimations(R.style.DialogAnimation)

        val editCountryCode : AppCompatEditText = dialog.findViewById(R.id.edtCountryCode)
        val editMobileNo : AppCompatEditText = dialog.findViewById(R.id.edtPhone)
        val btnUpdate : MaterialButton = dialog.findViewById(R.id.btnUpdate)

        editCountryCode.setText("+91")

        ccp?.setOnCountryChangeListener {
            editCountryCode.setText(ccp?.selectedCountryCodeWithPlus)
        }

        editCountryCode.setOnClickListener {
            ccp?.launchCountrySelectionDialog()

        }

        btnUpdate.setOnClickListener {
            when {
                editMobileNo.getTrimText().isBlank() -> {
                    showToast(resources.getString(R.string.lbl_please_enter_phone_number))
                }
                editMobileNo.getTrimText().length < 7 -> showToast(resources.getString(R.string.lbl_please_enter_valid_phone_number))

                binding.txtMobileNo.text.toString() == "${editCountryCode.text.toString().trim()} ${editMobileNo.getTrimText()}" -> {
                    showToast(resources.getString(R.string.msg_please_enter_new_number))
                }
                else -> {
                    hideSoftKeyboard()
                    viewModel.isTimer.value = false
                    tempMobileNo = editMobileNo.getTrimText()
                    tempCountryCode = editCountryCode.text.toString()
                    viewModel.updateMobile(editCountryCode.text.toString().trim(), editMobileNo.text.toString().trim())
                    dialog.dismiss()
                }
            }
        }

        dialog.show()
    }

    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }

}