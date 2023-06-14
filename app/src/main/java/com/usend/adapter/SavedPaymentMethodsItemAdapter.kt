package com.usend.adapter

import android.annotation.SuppressLint
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.ItemAddNewAddressBinding
import com.usend.databinding.RowSavedPaymentMethodsItemBinding
import com.usend.models.SavedCardsModel
import com.usend.utils.*
import com.usend.utils.CommonUtils.showAffirmationDialog
import com.usend.utils.DateTimeUtil.MM
import com.usend.utils.DateTimeUtil.MMM

class SavedPaymentMethodsItemAdapter
    (
    private val items: ArrayList<SavedCardsModel>,
    val callback: (Int, String) -> Unit
) : BaseListAdapter(items) {

    private var viewBindHelper = ViewBinderHelper()

    override fun getItemViewType(position: Int): Int {

        return if (position == 0)
            R.layout.item_add_new_address
        else {

            R.layout.row_saved_payment_methods_item
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowSavedPaymentMethodsItemBinding) {

            viewBindHelper.bind(holder.binding.swipe, position.toString())
            viewBindHelper.setOpenOnlyOne(true)
            viewBindHelper.lockSwipe(position.toString())
            holder.binding.txtCardNumber.text = "**** **** **** " + items[position].card.last_4
            holder.binding.txtTitle.text = items[position].card.card_brand
            holder.binding.isSelected = items[position].isSelected
            holder.binding.txtExpDate.text = holder.itemView.context.resources.getString(R.string.lbl_expires) + " " +
             DateTimeUtil.getConvertedTime(MM, MMM, items[position].card.exp_month.toString()) + " " + items[position].card.exp_year.toString()

            holder.binding.imgDelete.setOnClickListener {

                holder.itemView.context.showAffirmationDialog(title = holder.itemView.context.resources.getString(R.string.msg_are_you_sure_to_delete_card),
                    btnText = holder.itemView.context.resources.getString(R.string.lbl_yes),
                    btnNegativeText = holder.itemView.context.resources.getString(R.string.lbl_no))
                {
                    callback(holder.adapterPosition, DELETE)
                }
            }

            holder.binding.imgEdit.setOnClickListener {
                callback(holder.adapterPosition, EDIT)
            }

            holder.binding.flFront.setOnClickListener {
                callback(holder.adapterPosition, SELECTION)
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