package com.usend.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<T> : BaseActivity() where T : ViewDataBinding {

    protected abstract val layoutId: Int
    protected lateinit var binding: T
    protected open fun getDataFromIntent() {}
    protected open fun initControls() {}
    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        getDataFromIntent()
        initView()
        initControls()
    }
}
