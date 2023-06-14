package com.usend.adapter

import android.annotation.SuppressLint
import android.view.View
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.ItemAddNewAddressBinding
import com.usend.databinding.RowSelectSavedCardItemBinding
import com.usend.models.SelectCardItemModel
import com.usend.utils.ADD
import com.usend.utils.DateTimeUtil
import com.usend.utils.EDIT

class SelectSavedCardsItemAdapter(
    private val items: ArrayList<SelectCardItemModel>,
    val isCheckBoxVisible: Boolean = true,
    val callback: (Int, String) -> Unit
) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int): Int {

        return if (position == 0)
            R.layout.item_add_new_address
        else {

            R.layout.row_select_saved_card_item
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowSelectSavedCardItemBinding) {
            holder.binding.isSelected = items[position].isSelected

            holder.binding.txtCardNumber.text = "**** **** **** " + items[position].model?.last_4
            holder.binding.txtTitle.text = items[position].model?.card_brand
            holder.binding.txtExpDate.text =
                holder.itemView.context.resources.getString(R.string.lbl_expires) + " " +
                        DateTimeUtil.getConvertedTime(
                            DateTimeUtil.MM,
                            DateTimeUtil.MMM,
                            items[position].model?.exp_month.toString()
                        ) + " " + items[position].model?.exp_year.toString()


            if (isCheckBoxVisible) {
                holder.binding.rbItem.visibility = View.VISIBLE
            } else {
                holder.binding.rbItem.visibility = View.GONE
            }

            holder.binding.flFront.setOnClickListener {
                callback(holder.adapterPosition, EDIT)
            }

        } else if (holder.binding is ItemAddNewAddressBinding) {
            holder.binding.txtAddress.text =
                holder.itemView.context.resources.getString(R.string.lbl_add_new_card)
            holder.itemView.setOnClickListener {
                callback(holder.adapterPosition, ADD)
            }
        }
    }
}