package com.usend.views

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.usend.R
import com.usend.base.ViewModelActivity
import com.usend.databinding.ActivityConciergeForGuestBinding
import com.usend.viewmodels.HomeViewModel
import com.usend.views.home.ConciergeFragmentForGuest
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlin.reflect.KClass

class ConciergeForGuestActivity(
    override val modelClass: KClass<HomeViewModel> = HomeViewModel::class,
    override val layoutId: Int = R.layout.activity_concierge_for_guest
) : ViewModelActivity<ActivityConciergeForGuestBinding, HomeViewModel>() {

    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }

    override fun initControls() {
        initToolbar(
            toolbar = toolbar,
            title = resources.getString(R.string.lbl_premium_membership_choose),
            isBackVisible = true,
            isNoti = false
        )

        commonFragmentCallWithoutBackStack(ConciergeFragmentForGuest())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun commonFragmentCallWithoutBackStack(fragment: Fragment?) {
        val cFragment: Fragment? = fragment
        if (cFragment != null) {
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            fragmentTransaction.replace(R.id.main_frame, cFragment)
            fragmentTransaction.commit()
        }
    }
}