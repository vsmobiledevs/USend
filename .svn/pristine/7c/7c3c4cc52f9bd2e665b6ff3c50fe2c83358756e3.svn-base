package com.usend.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class ItemOffsetDecoration(private val mItemOffset: Int, private val edge : String, private val pos : Int = 0) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition

        if (position == pos) {

            if (edge == "left") {
                outRect.left = mItemOffset
            }
            else if(edge == "top")
            {
                outRect.top = mItemOffset
            }
            else if(edge == "bottom")
            {
                outRect.bottom = mItemOffset
            }
        }
    }
}