package com.example.testbank.base.bindingadapter

import androidx.databinding.BindingAdapter
import com.rd.PageIndicatorView

@BindingAdapter("indicatorCount")
fun PageIndicatorView.setIndicatorCount(indicatorCount: Int) {
    count = indicatorCount
}