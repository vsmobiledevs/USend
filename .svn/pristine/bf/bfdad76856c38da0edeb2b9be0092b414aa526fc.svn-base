package com.usend.views.userauth

import android.content.Intent
import com.usend.R
import com.usend.base.BaseViewModel
import com.usend.base.ViewModelFragment
import com.usend.databinding.FragmentAuthBinding

class AuthFragment(
    override val modelClass: Class<BaseViewModel> = BaseViewModel::class.java,
    override val layoutId: Int = R.layout.fragment_auth
) : ViewModelFragment<FragmentAuthBinding, BaseViewModel>() {

    override fun initControls() {

        binding.authFrag = this
    }

    fun onSignInClick() {
        SignInActivity.newIntent(requireActivity(), Intent(requireActivity(), SignInActivity::class.java))
    }

    fun onSignUpClick() {
        SignUpActivity.newIntent(requireActivity(), Intent(requireActivity(), SignUpActivity::class.java))
    }
}