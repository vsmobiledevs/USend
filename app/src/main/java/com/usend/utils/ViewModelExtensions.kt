package com.usend.utils


import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.usend.comman.ViewModelProviderFactory


fun <T : ViewModel> FragmentActivity.getViewModel(modelClass: Class<T>): T {
    return ViewModelProvider(this, ViewModelProviderFactory(this.application))[modelClass]
}



