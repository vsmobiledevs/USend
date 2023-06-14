package com.usend.adapter

import com.base.network.model.UserNotification
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowItemNotiBinding
import com.usend.extensions.nullSafe
import com.usend.utils.DELETE
import com.usend.utils.DateTimeUtil.dd_MM_yyyy_hh_mm_a
import com.usend.utils.DateTimeUtil.timeStamptoDate
import com.usend.utils.NORMAL

class NotificationItemAdapter(private val items: ArrayList<UserNotification>, val callback : (Int, String) -> Unit) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int) = R.layout.row_item_noti
    private var viewBindHelper = ViewBinderHelper()

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if(holder.binding is RowItemNotiBinding)
        {

            viewBindHelper.bind(holder.binding.swipe, position.toString())
            viewBindHelper.setOpenOnlyOne(true)

            holder.binding.isRead = items[position].isRead

            holder.binding.txtNoti.text = items[position].message
            holder.binding.txtDate.text = timeStamptoDate(items[position].createdAt.nullSafe().toLong(), dd_MM_yyyy_hh_mm_a)

            holder.binding.imgDelete.setOnClickListener {
                viewBindHelper.closeLayout(position.toString())
                callback(holder.adapterPosition, DELETE)
            }

            holder.binding.flFront.setOnClickListener {
                callback(holder.adapterPosition, NORMAL)
            }
        }
    }
}