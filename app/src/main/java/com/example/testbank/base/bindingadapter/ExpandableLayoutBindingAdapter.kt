package com.example.testbank.base.bindingadapter

import androidx.databinding.BindingAdapter
import net.cachapa.expandablelayout.ExpandableLayout

@BindingAdapter("expandableLayout")
fun ExpandableLayout.setExpand(expand: Boolean){
    setExpanded(expand,true)
}