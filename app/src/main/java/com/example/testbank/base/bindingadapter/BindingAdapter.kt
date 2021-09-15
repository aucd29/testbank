@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.example.testbank.base.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOrInvisible")
fun View.setVisibleOrInvisible(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}