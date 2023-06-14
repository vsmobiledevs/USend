package com.usend.adapter


import android.annotation.SuppressLint
import com.base.network.model.OrderPackageDetails
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowPackageDetailsBinding
class PackageListItemAdapter(
    private val items: ArrayList<OrderPackageDetails>,
    val callback: (Int) -> Unit
) : BaseListAdapter(items) {

    override fun getItemViewType(position: Int) = R.layout.row_package_details

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowPackageDetailsBinding) {

            holder.binding.txtPackageID.text = items[position].packageNumber
            holder.binding.txtDimensions.text = items[position].dimensions + " " + items[position].dimensionsUnit
            holder.binding.txtWeight.text = String.format("%.2f", items[position].weight!!.toDouble()) + " " + items[position].weightUnit

            holder.itemView.setOnClickListener {

                callback(holder.adapterPosition)
            }
        }
    }
}