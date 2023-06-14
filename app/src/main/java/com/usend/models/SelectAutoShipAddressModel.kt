package com.usend.models

import com.base.network.model.GetAutoshipmentServiceListData

data class SelectAutoShipAddressModel(
    var model: GetAutoshipmentServiceListData,
    var isSelected: Boolean
)