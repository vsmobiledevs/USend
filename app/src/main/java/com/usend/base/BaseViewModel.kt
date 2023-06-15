package com.usend.base

import android.app.Application
import com.usend.utils.PreferenceHelper.customPrefs
import com.usend.utils.PreferenceHelper.set
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.usend.extensions.nullSafe
import com.usend.utils.*


abstract class BaseViewModel(application: Application) : RuntimePermissionViewModel(application) {

    val prefs by lazy {
        application.customPrefs()
    }

    fun getAuthKey() : String
    {
        return prefs.getString(AUTH_KEY,"").nullSafe()
    }
    fun getUserId() : Int
    {
        return prefs.getInt(USER_ID,0).nullSafe()
    }
    fun getAuth():String
    {
        return prefs.getString(SQUARE_UP_TOKEN,"").nullSafe()
    }

    fun generateFcmToken() {

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    JLog.e(Companion.TAG, "getInstanceId failed ${task.exception}")
                    return@OnCompleteListener
                }
                // Get new Instance ID token
                val token = task.result?.token
                JLog.d(Companion.TAG, "BaseViewModel: Token $token")
                prefs[PREF_FCM_TOKEN] = token
            })
    }

    companion object {
        private val TAG = BaseViewModel::class.java.simpleName
    }


}
