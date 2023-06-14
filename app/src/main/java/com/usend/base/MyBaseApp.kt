package com.usend.base

import android.content.SharedPreferences
import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDexApplication
import com.usend.utils.PreferenceHelper
import com.usend.utils.PreferenceHelper.customPrefs

class MyBaseApp : MultiDexApplication(), LifecycleObserver {


    private val prefs: SharedPreferences by lazy {
        customPrefs()
    }

    override fun onCreate() {
        super.onCreate()
        myBaseApp = this
        PreferenceHelper.init(this)

    }

    companion object {

        private lateinit var myBaseApp: MyBaseApp

        val getInstance: MyBaseApp
            get() {
                return myBaseApp
            }
    }
}