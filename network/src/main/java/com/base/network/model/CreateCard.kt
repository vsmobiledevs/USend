package com.base.network.model

data class CreateCard(var idempotency_key:String,var source_id:String ,var card: Cards)
{
    data class Cards(var cardholder_name:String?,var customer_id:String?,var exp_month:Int,var exp_year:Int)
}
