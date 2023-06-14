package com.base.network.model

data class CreateSubscription(var customer_id:String,var location_id:String,var plan_id:String,var card_id:String,var idempotency_key:String)
