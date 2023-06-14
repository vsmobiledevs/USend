package com.usend.repository

import com.base.network.ApiClient
import com.base.network.api.AutoShipmentApi
import com.base.network.api.PackageApi



class ApiServices {
    companion object {
        private lateinit var packageApi: PackageApi
        private lateinit var autoShipmentApi: AutoShipmentApi

        fun getCouponApiService(): PackageApi {
            if (!Companion::packageApi.isInitialized) {
                packageApi = ApiClient().createService(PackageApi::class.java)
            }
            return packageApi
        }

        fun getAutoShipmentApiService(): AutoShipmentApi {
            if (!Companion::autoShipmentApi.isInitialized) {
                autoShipmentApi = ApiClient().createService(AutoShipmentApi::class.java)
            }
            return autoShipmentApi
        }


    }

}





