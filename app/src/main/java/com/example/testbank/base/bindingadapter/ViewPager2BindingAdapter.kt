package com.example.testbank.base.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("bindItems")
fun <T> ViewPager2.bindItems(items: List<T>?) {
    adapter?.let {
        when (it) {
            is ListAdapter<*, *> -> {
                val listAdapter = it as ListAdapter<T, RecyclerView.ViewHolder>
                listAdapter.submitList(items)
            }
            else -> error("unknown adapter")
        }
    }
}