package com.usend.viewmodels
import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.usend.repository.AuthenticationRepository
import com.usend.base.BaseViewModel

class IntroViewModel(application: Application, private var repository: AuthenticationRepository):
    BaseViewModel(application){

    val mContext: Context = application.applicationContext

    val status : MediatorLiveData<Any> by lazy {
        MediatorLiveData<Any>()
    }

    val currentPageIndex : MediatorLiveData<Int> by lazy {
        MediatorLiveData<Int>()
    }
}