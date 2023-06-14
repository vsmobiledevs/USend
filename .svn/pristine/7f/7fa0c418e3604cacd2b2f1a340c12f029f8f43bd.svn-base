package com.usend.views.userauth

import android.content.Context
import android.content.Intent
import androidx.viewpager.widget.ViewPager
import com.usend.R
import com.usend.adapter.IntroPageItemAdapter
import com.usend.base.ViewModelActivity
import com.usend.databinding.ActivityIntroBinding
import com.usend.viewmodels.IntroViewModel
import com.usend.views.MainActivity
import kotlin.reflect.KClass

class IntroActivity(override val modelClass: KClass<IntroViewModel> = IntroViewModel::class, override val layoutId: Int = R.layout.activity_intro) : ViewModelActivity<ActivityIntroBinding, IntroViewModel>() {


    override fun initView() {
        super.initView()

        binding.viewmodel = viewModel
        binding.introActivity = this
        binding.introPager.adapter = IntroPageItemAdapter()
        binding.introPager.addOnPageChangeListener(object :
            ViewPager.SimpleOnPageChangeListener() {

            override fun onPageSelected(position: Int) {
                viewModel.currentPageIndex.value = position
            }
        })

        binding.introIndicator.setViewPager(binding.introPager)
    }

    override fun initControls() {
    }

    fun onSkipClick()
    {
        MainActivity.newIntent(this, Intent(this, MainActivity::class.java))
        finish()
    }

    fun onNextClick()
    {
        viewModel.currentPageIndex.value = binding.introPager.currentItem + 1
        binding.introPager.currentItem = viewModel.currentPageIndex.value!!
    }

    fun onGetStartedClick()
    {
        MainActivity.newIntent(this, Intent(this, MainActivity::class.java))
        finish()
    }

    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }

}
