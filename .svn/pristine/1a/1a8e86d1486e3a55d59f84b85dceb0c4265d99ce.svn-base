package com.usend.binder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter


@BindingAdapter("android:visibility")
fun View.visibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}



@InverseBindingAdapter(attribute = "date")
fun getDate(textView: AppCompatTextView): Long? {
    return if (textView.tag != null) textView.tag as Long else null
}


@InverseBindingAdapter(attribute = "time")
fun getTime(textView: AppCompatTextView): Long? {
    return if (textView.tag != null) textView.tag as Long else null
}