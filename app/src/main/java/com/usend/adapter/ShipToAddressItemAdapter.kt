package com.usend.adapter

import android.view.View
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.ItemAddNewAddressBinding
import com.usend.databinding.RowShipToAddressItemBinding
import com.usend.models.SelectShipAddressModel

class ShipToAddressItemAdapter(
    private val items: ArrayList<SelectShipAddressModel>,
    val callback: (Int) -> Unit,
    private val isCheckBoxVisible: Boolean = true
) : BaseListAdapter(items) {



    override fun getItemViewType(position: Int): Int {

        return if (position == 0)
            R.layout.item_add_new_address
        else {

            R.layout.row_ship_to_address_item
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowShipToAddressItemBinding) {
            var address: String

            address = items[position].model.street1 + ",\n"

            if (items[position].model.street2!!.isNotEmpty()) {
                address = address + items[position].model.street2 + ",\n"
            }
            if (items[position].model.city!!.isNotEmpty()) {
                address = address + items[position].model.city + ", "
            }
            if (items[position].model.state!!.isNotEmpty()) {
                address = address + items[position].model.state + ", "
            }
            if (items[position].model.country!!.isNotEmpty()) {
                address += items[position].model.country
            }
            if (items[position].model.postalCode!!.isNotEmpty()) {
                address = address + " - " + items[position].model.postalCode
            }

            holder.binding.txtAddress.text = address

            holder.binding.txtLabel.text = "${items[position].model.label}"
            holder.binding.txtReceiverName.text = "${items[position].model.receiverName}"

            holder.binding.isSelected = items[position].isSelected

            if (isCheckBoxVisible) {
                holder.binding.rbItem.visibility = View.VISIBLE
            } else {
                holder.binding.rbItem.visibility = View.GONE
            }

            holder.itemView.setOnClickListener {
                callback(holder.adapterPosition)
            }
        } else if (holder.binding is ItemAddNewAddressBinding) {
            holder.itemView.setOnClickListener {
                callback(holder.adapterPosition)
            }
        }
    }
}