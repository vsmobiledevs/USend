package com.usend.adapter


import com.base.network.model.CarrierList
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowShippingMethodFilterItemBinding

class ShippingMethodFilterItemAdapter(private val items: ArrayList<CarrierList>, val callback : (Int) -> Unit) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int) = R.layout.row_shipping_method_filter_item

    private var preSelected : RowShippingMethodFilterItemBinding? = null

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if(holder.binding is RowShippingMethodFilterItemBinding)
        {
            holder.binding.txtFilterItem.text = items[position].name

            if(position == 0)
            {
                holder.binding.isSeleted = true
                preSelected = holder.binding
                callback(holder.adapterPosition)
            }

            holder.itemView.setOnClickListener {

                preSelected?.isSeleted = false
                holder.binding.isSeleted = true
                preSelected = holder.binding
                callback(holder.adapterPosition)
            }
        }
    }
}