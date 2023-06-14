package com.usend.base

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.usend.extensions.layoutInflater

abstract class BaseListAdapter(private val items: ArrayList<out Any?>) : RecyclerView.Adapter<BaseListAdapter.ItemViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(DataBindingUtil.inflate(parent.context.layoutInflater(), viewType, parent, false))

    class ItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}
