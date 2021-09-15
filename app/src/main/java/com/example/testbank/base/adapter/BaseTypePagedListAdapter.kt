package com.example.testbank.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.paging.PagedListAdapter


/**
 * Paged List 를 위한 공용 어뎁터
 *
 * @param T 데이터 타입
 * @property residMap 레이아웃 아이디
 * @property viewModel 뷰 모델
 * @property viewDataBindingCallback 바인딩 뷰 홀더 콜백
 */
open class BaseTypePagedListAdapter<T: ListAdapterViewType<*>>(
    open val residMap: Map<Int, Int>,
    open var viewModel: ViewModel? = null,
    var viewDataBindingCallback: ((ViewDataBinding, Int) -> Unit)? = null
) : PagedListAdapter<T, BaseViewHolder<T>>(BaseDiffer<T>()) {
    constructor(
        resid: Int,
        viewModel: ViewModel? = null,
        viewDataBindingCallback: ((ViewDataBinding, Int) -> Unit)? = null
    ) : this(mapOf(0 to resid), viewModel, viewDataBindingCallback)

    var adapterLifecycleOwner: LifecycleOwner? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = residMap[viewType]?.let { resid ->
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, resid, parent, false).apply {
                adapterLifecycleOwner?.let(this::setLifecycleOwner)
            }
        } ?: error("resid == null (viewType = $viewType)")

        return BaseViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return currentList?.let {
            val item = it[position]
            if (item is ListAdapterViewType<*>) {
                item.viewType
            } else {
                super.getItemViewType(position)
            }
        } ?: super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        viewDataBindingCallback?.invoke(holder.binding, position)
        getItem(position)?.let {
            holder.bind(it, viewModel)
        }
    }
}