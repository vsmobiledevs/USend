package com.usend.models

import com.base.network.model.CardList

data class SelectCardItemModel(
    var model: CardList? = null,
    var isSelected: Boolean? = null
)