package com.usend.adapter

import android.annotation.SuppressLint
import com.base.network.model.ServiceChargeList
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowExtraServicesItemBinding

class ExtraServicesItemAdapter(
    private val items: ArrayList<ServiceChargeList>,
    val callback: (Int) -> Unit
) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int) = R.layout.row_extra_services_item

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowExtraServicesItemBinding) {

            holder.binding.txtServiceTitle.text = items[position].chargeName
            holder.binding.txtServicePrice.text = "$" + (items[position].price?.toInt()?.toString()?:"0")
            holder.binding.isSelected = false

            holder.itemView.setOnClickListener {

                holder.binding.isSelected = !holder.binding.isSelected!!
                callback(holder.adapterPosition)
            }
        }
    }
}