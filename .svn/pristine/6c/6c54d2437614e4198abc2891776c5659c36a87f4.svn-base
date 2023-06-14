package com.usend.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.spaceo.basecode.CustomProgressDialog
import com.usend.R
import com.usend.utils.*
import com.usend.utils.PreferenceHelper.set
import com.usend.views.MainActivity


open class BaseFragment : Fragment() {

    val prefs: SharedPreferences by lazy {
        baseActivity.prefs
    }

    private val baseActivity: BaseActivity by lazy {
        activity as BaseActivity
    }

    private var mProgressDialog: CustomProgressDialog? = null

    fun showProgressDialog(
        mContext: Context,
        title: String = "",
        message: String = getString(R.string.default_loading_message)
    ) {
        mProgressDialog = CustomProgressDialog(mContext)
        mProgressDialog?.let {
            it.setCanceledOnTouchOutside(false)
            it.setCancelable(false)
            if (title.isNotEmpty())
                it.setTitle(title)
            it.setMessage(message)
            it.show()
        }
    }
    fun hideProgressDialog() {
        mProgressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
                mProgressDialog = null
            }
        }
    }

    fun startActivityForResult(intent: Intent, callback: (data: Intent?) -> Unit) {
        startActivityForResult(intent) { resultCode, data ->
            if (resultCode == Activity.RESULT_OK) {
                callback(data)
            }
        }
    }

    private lateinit var resultCallback: (resultCode: Int, data: Intent?) -> Unit

    private fun startActivityForResult(intent: Intent, callback: (resultCode: Int, data: Intent?) -> Unit) {
        resultCallback = callback
        startActivityForResult(intent, 65535)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (::resultCallback.isInitialized && requestCode == 65535)
            resultCallback(resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun showTokenExpiredDialog(message: String) {
        CommonUtils.showOkCallBackDialog(requireActivity(), getString(R.string.lbl_session_expired), message) {
            clearDataAndLogout()
        }
    }

    private fun clearDataAndLogout() {
        prefs[AUTH_KEY] = ""
        prefs[IS_LOGIN] = false
        MainActivity.newIntent(
            requireActivity(), Intent(requireActivity(), MainActivity::class.java)
            .putExtra(FROM, FROM_LOGOUT)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        requireActivity().finishAffinity()
    }


}