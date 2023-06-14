package com.usend.views.home

import android.content.Intent
import com.usend.R
import com.usend.base.BaseViewModel
import com.usend.base.ViewModelFragment
import com.usend.databinding.FragmentMyProfileForGuestBinding
import com.usend.utils.SLUG
import com.usend.utils.TITLE
import com.usend.views.userauth.SignInActivity
import com.usend.views.userauth.SignUpActivity

class MyProfileForGuest(
    override val modelClass: Class<BaseViewModel> = BaseViewModel::class.java,
    override val layoutId: Int = R.layout.fragment_my_profile_for_guest
) : ViewModelFragment<FragmentMyProfileForGuestBinding, BaseViewModel>() {

    override fun initControls() {

        binding.frag = this

        binding.txtProhibitedItems.setOnClickListener {
            //ProhibitedItemsActivity.newIntent(activity!!, Intent(activity!!, ProhibitedItemsActivity::class.java))
            /*val browserIntent = Intent(
                Intent.ACT
                ION_VIEW,
                Uri.parse(PROHIBITED_ITEMS_URL)
            )
            startActivity(browserIntent)*/
            ProhibitedItemsActivity.newIntent(
                requireActivity(),
                Intent(requireActivity(), ProhibitedItemsActivity::class.java)
            )
        }

        binding.txtTandC.setOnClickListener {
            WebViewActivity.newIntent(
                requireActivity(), Intent(requireActivity(), WebViewActivity::class.java)
                    .putExtra(TITLE, resources.getString(R.string.lbl_t_and_c_))
                    .putExtra(SLUG, "terms_and_conditions")
            )
        }

        binding.txtPrivacyPolicy.setOnClickListener {
            WebViewActivity.newIntent(
                requireActivity(), Intent(requireActivity(), WebViewActivity::class.java)
                    .putExtra(TITLE, resources.getString(R.string.lbl_privacy_policy))
                    .putExtra(SLUG, "privacy_policy")
            )
        }
    }

    fun onSignInClick() {
        SignInActivity.newIntent(requireActivity(), Intent(requireActivity(), SignInActivity::class.java))
    }

    fun onSignUpClick() {
        SignUpActivity.newIntent(requireActivity(), Intent(requireActivity(), SignUpActivity::class.java))
    }

}