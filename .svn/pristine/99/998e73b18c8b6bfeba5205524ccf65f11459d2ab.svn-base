package com.usend.adapter

import android.view.View
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowAutoShippingMethodItemBinding
import com.usend.extensions.loadImageUrl
import com.usend.models.SelectAutoShipAddressModel
import com.usend.utils.CommonUtils
import java.util.*

class AutoShippingMethodItemAdapter(
    private val items: ArrayList<SelectAutoShipAddressModel>,
    val isCheckBoxVisible: Boolean = true,
    val callback: (Int) -> Unit
) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int) = R.layout.row_auto_shipping_method_item

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowAutoShippingMethodItemBinding) {

            holder.binding.txtShippingMethodName.text = items[position].model.serviceName
            holder.binding.txtTimeToShipped.text = items[position].model.carrierName

            holder.binding.isSelected = items[position].isSelected
            holder.binding.imgShipping.loadImageUrl(
                holder.itemView.context,
                items[position].model.image!!,
                cornerRadius = CommonUtils.dpToPx(holder.itemView.context, 20),
                placeholder = R.drawable.ic_package_place_holder,
                errorPlaceholder = R.drawable.ic_package_place_holder
            )
            {

            }

            if (isCheckBoxVisible) {
                holder.binding.rbItem.visibility = View.VISIBLE
            } else {
                holder.binding.rbItem.visibility = View.GONE
            }

            holder.itemView.setOnClickListener {
                callback(holder.adapterPosition)
            }
        }
    }
}