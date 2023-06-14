package com.usend.adapter

import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowItemRegionSelectionBinding

class RegionSelectionItemAdapter(private val items: ArrayList<String>, val callback : (Int) -> Unit) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int) = R.layout.row_item_region_selection

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if(holder.binding is RowItemRegionSelectionBinding)
        {

            holder.binding.txtItem.text = items[position]

            holder.itemView.setOnClickListener {
                callback(holder.adapterPosition)
            }
        }
    }
}