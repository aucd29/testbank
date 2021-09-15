package com.example.testbank.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.testbank.BR

/**
 * BaseTypeListAdapter 에서 공통적으로 사용하기 위한 ViewHolder 이다.
 * 이를 이용하기 이용하기 위해서는 xml 에서 Model 의 경우 item 으로 ViewModel 의 경우 vm 으로
 * 설정해야 한다.
 */
class BaseViewHolder<T>(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T, viewmodel: ViewModel?) {
        binding.setVariable(BR.item, item)
        viewmodel?.let {
            binding.setVariable(BR.vm, it)
        }
        binding.executePendingBindings()
    }
}