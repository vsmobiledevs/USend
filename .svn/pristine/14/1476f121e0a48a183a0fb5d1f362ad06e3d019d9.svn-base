package com.usend.views
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import com.usend.R
import com.usend.base.ViewModelActivity
import com.usend.viewmodels.SplashViewModel
import com.usend.databinding.ActivitySplashBinding
import com.usend.utils.IS_LOGIN
import com.usend.views.userauth.IntroActivity
import kotlin.reflect.KClass

@SuppressLint("CustomSplashScreen")
class SplashActivity : ViewModelActivity<ActivitySplashBinding, SplashViewModel>() {

    override val modelClass: KClass<SplashViewModel> = SplashViewModel::class
    override val layoutId: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.generateFcmToken()



        viewModel.splashNavigate.observe(
            this
        ) {
            if (it == true) {

                if (prefs.getBoolean(IS_LOGIN, false)) {
                    MainActivity.newIntent(this, Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    IntroActivity.newIntent(this, Intent(this, IntroActivity::class.java))
                    finish()
                }

            } else {
                //fallback here
            }
        }
        viewModel.startTimer()
    }

    override fun initControls() {

    }
}