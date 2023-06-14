package com.usend.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.base.BaseViewModel
import com.usend.repository.UserRepository

class OrderHistoryViewModel(application: Application, private var repository: UserRepository) :
    BaseViewModel(application) {

    val mContext: Context = application.applicationContext

    val status: MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val email by lazy { MediatorLiveData<String>() }
    val password by lazy { MediatorLiveData<String>() }

}