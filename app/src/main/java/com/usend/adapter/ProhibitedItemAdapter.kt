package com.usend.adapter

import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowProhibitedItemBinding
import com.usend.models.ProhibitedItemModel

class ProhibitedItemAdapter(private val items: ArrayList<ProhibitedItemModel>, val callback : (Int) -> Unit) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int) = R.layout.row_prohibited_item

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if(holder.binding is RowProhibitedItemBinding)
        {

            holder.binding.txtProhibitedItem.text = items[position].title

            holder.binding.imgProhibitedItem.setImageResource(items[position].img)

            holder.itemView.setOnClickListener {
                callback(holder.adapterPosition)
            }
        }
    }
}