package com.usend.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.core.content.ContextCompat.getColor
import com.base.network.model.ConciergeRequestResponseData
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowConciergeItemBinding
import com.usend.extensions.loadImageUrl
import com.usend.utils.*

class ConciergeItemAdapter(
    private val items: ArrayList<ConciergeRequestResponseData>,
    val callback: (Int, String) -> Unit
) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int) = R.layout.row_concierge_item

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowConciergeItemBinding) {

            holder.itemView.setOnClickListener {
                callback(holder.adapterPosition, NORMAL)
            }

            holder.binding.btnPay.setOnClickListener {
                callback(holder.adapterPosition, PAY)
            }

            holder.binding.txtProductName.text = items[position].productName
            holder.binding.txtDate.text = items[position].createdDate
            holder.binding.txtPrice.text = CommonUtils.convertToPriceFormat(items[position].price!!.toDouble())

            if(!items[position].isPayment!! && items[position].payableAmount!!.toDouble() != 0.0)
            {
                holder.binding.btnPay.visibility = View.VISIBLE
                holder.binding.btnPay.text = holder.itemView.context.resources.getString(R.string.lbl_pay) + " $" + String.format("%.2f", items[position].payableAmount!!.toDouble())
            }
            else
            {
                holder.binding.btnPay.visibility = View.GONE
            }

            when (items[position].status) {
                PROCESSING -> {
                    holder.binding.txtStatus.setTextColor(getColor(holder.itemView.context, R.color.colorEEBB2E))
                }
                SUBMITTED -> {
                    holder.binding.txtStatus.setTextColor(getColor(holder.itemView.context, R.color.color154A99))
                }
                PURCHASED -> {
                    holder.binding.txtStatus.setTextColor(getColor(holder.itemView.context, R.color.color5A9E41))
                }
                NOT_AVAILABLE -> {
                    holder.binding.txtStatus.setTextColor(getColor(holder.itemView.context, R.color.colorE82D2D))
                }
                PENDING_PAYMENT -> {
                    holder.binding.txtStatus.setTextColor(getColor(holder.itemView.context, R.color.colorE82D2D))
                }
                else ->
                {
                    holder.binding.txtStatus.setTextColor(getColor(holder.itemView.context, R.color.color154A99))
                }
            }

            holder.binding.txtStatus.text = items[position].status

            holder.binding.imgConciergeItem.loadImageUrl(
                holder.itemView.context,
                items[position].productImage!!,
                cornerRadius = CommonUtils.dpToPx(holder.itemView.context, 10),
                placeholder = R.drawable.ic_package_place_holder,
                errorPlaceholder = R.drawable.ic_package_place_holder
            ) {

            }
        }
    }
}