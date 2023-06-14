package com.usend.adapter

import android.annotation.SuppressLint
import com.base.network.model.CardList
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.ItemAddNewAddressBinding
import com.usend.databinding.RowSavedCardItemBinding
import com.usend.utils.ADD
import com.usend.utils.CommonUtils.showAffirmationDialog
import com.usend.utils.DELETE
import com.usend.utils.DateTimeUtil
import com.usend.utils.EDIT


class SavedCardsItemAdapter(
    private val items: ArrayList<CardList>,
    val callback: (Int, String) -> Unit
) : BaseListAdapter(items) {

    private var viewBindHelper = ViewBinderHelper()

    override fun getItemViewType(position: Int): Int {

        return if (position == 0)
            R.layout.item_add_new_address
        else {
            R.layout.row_saved_card_item
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowSavedCardItemBinding) {
            viewBindHelper.setOpenOnlyOne(true)
          viewBindHelper.closeLayout(position.toString())
            viewBindHelper.bind(holder.binding.swipe, position.toString())

            holder.binding.txtCardNumber.text = "**** **** **** " + items[position].last_4
            holder.binding.txtTitle.text = items[position].card_brand
            holder.binding.txtExpDate.text = holder.itemView.context.resources.getString(R.string.lbl_expires) + " " +
                    DateTimeUtil.getConvertedTime(DateTimeUtil.MM, DateTimeUtil.MMM, items[position].exp_month.toString()) + " " + items[position].exp_year.toString()

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