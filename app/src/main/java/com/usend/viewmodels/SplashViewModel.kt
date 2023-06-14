package com.usend.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.usend.base.BaseViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashViewModel(application: Application) : BaseViewModel(application), CoroutineScope {

    var splashNavigate: MutableLiveData<Boolean> = MutableLiveData()

    fun startTimer() {
        try {
            launch(Dispatchers.Main){
                delay(2000)
                splashNavigate.postValue(true)
            }
        }catch (e:Exception){
            splashNavigate.postValue(false)
        }
    }
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}
