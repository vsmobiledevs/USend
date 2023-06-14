package com.usend.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.network.model.CarrierList
import com.base.network.model.GetRatesList
import com.usend.R
import com.usend.base.BaseListAdapter
import com.usend.databinding.RowShippingMethodItemBinding
import com.usend.databinding.ShimmerParentLayoutBinding
import com.usend.databinding.ShimmerPlaceholderLayoutBinding
import com.usend.extensions.loadImageUrl
import com.usend.utils.*

class ShippingMethodItemAdapter(
    private val items: ArrayList<GetRatesList>,
    private val carrierList: List<CarrierList>,
    val callback: (Int) -> Unit
) : BaseListAdapter(items) {
    private var wizardView: View? = null
    private var mContext: Context? = null
    private var mViewGroup: ViewGroup? = null

    override fun getItemViewType(position: Int): Int {
        if (position >= items.size) {
            return R.layout.shimmer_parent_layout
        } else {
            return R.layout.row_shipping_method_item
        }
    }

    override fun getItemCount(): Int {
        return items.size + carrierList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        mContext = parent.context
        mViewGroup = parent
        return super.onCreateViewHolder(parent, viewType)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        if (holder.binding is RowShippingMethodItemBinding && items.isNotEmpty()) {

            holder.binding.txtShippingMethodName.text = items[position].serviceName
            holder.binding.txtShippingCharges.text =
                "$" + String.format("%.2f", items[position].shipmentCost?.toDouble())
            holder.binding.txtTimeToShipped.text = items[position].deliveryTime
            holder.binding.txtAvailability.text = "Available"

            if (items[position].serviceCode.equals(RECOMMENDED_FEDEX_INTERNATIONAL_PRIO_SERVICE_CODE) ||
                items[position].serviceCode.equals(RECOMMENDED_DHL_EXPRESS_WORLDWIDE_SERVICE_CODE) ||
                items[position].serviceCode.equals(RECOMMENDED_DHL_FEDEX_PRIO_OVERNIGHT_SERVICE_CODE) ||
                items[position].serviceCode.equals(RECOMMENDED_DHL_FEDEX_2_DAY_SERVICE_CODE)
            ) {
                holder.binding.txtRecommanded.visibility = View.VISIBLE
            } else {
                holder.binding.txtRecommanded.visibility = View.GONE
            }
            holder.binding.imgShipping.loadImageUrl(
                holder.itemView.context,
                items[position].image!!,
                cornerRadius = CommonUtils.dpToPx(holder.itemView.context, 20),
                placeholder = R.drawable.ic_package_place_holder,
                errorPlaceholder = R.drawable.ic_package_place_holder
            )
            {

            }

            holder.itemView.setOnClickListener {
                callback(holder.adapterPosition)
            }
        } else {
            if (holder.binding is ShimmerParentLayoutBinding) {
                holder.binding.dynamic.removeAllViews()
                wizardView = ShimmerPlaceholderLayoutBinding.inflate(
                    LayoutInflater.from(mContext),
                    holder.binding.dynamic,
                    false
                ).root
                holder.binding.simmerLayout.startShimmer()
                holder.binding.simmerLayout.visibility = View.VISIBLE
                // add the inflated View to the layout
                holder.binding.dynamic.addView(wizardView)
                //   carrierView.add(ViewList(wizardView, cl.code))
            }
        }
    }
}