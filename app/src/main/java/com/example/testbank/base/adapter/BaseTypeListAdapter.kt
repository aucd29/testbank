package com.example.testbank.base.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * BaseTypeListAdapter 에 사용하는 Model 의 경우 ListAdapterViewType 를 정의해서 사용해야 하여 그 예는
 * 아래와 같다.
 *
 * ```kotlin
 * data class YourModel(
        val id: Int,
        val name: String? = null,
        override val viewType: Int = T_INVESTMENT_TYPE
    ) : ListAdapterViewType<Int> {
        override fun diff(): Int =
            id

        companion object {
            val T_INVESTMENT_TYPE = 0
        }
    }
 * ```
 */
interface ListAdapterViewType<DIFF> {
    /**
     * ListAdapter 의 ItemViewType 을 지정 한다.
     */
    val viewType: Int

    /**
     * DiffUtil 에서 비교를 위한 값을 정의 한다.
     */
    fun diff(): DIFF
}

/**
 * BaseTypeListAdapter 에서 사용하는 DiffUtil 을 공통으로 사용하기 위해 만든 클래스
 */
class BaseDiffer<DT: ListAdapterViewType<*>> : DiffUtil.ItemCallback<DT>() {
    override fun areItemsTheSame(oldItem: DT, newItem: DT): Boolean =
        oldItem.diff() == newItem.diff()
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DT, newItem: DT): Boolean =
        oldItem == newItem
}

/**
 * 하나의 ListAdapter 로 RecyclerView 의 일반적인 상황을 대응하기 위해 만든 클래스 이며 사용 예는 아래와 같다.
 *
 * @param residMap viewType 값 과 레이아웃 아이디를 정의해서 map 을 전달 한다. 이때 viewType 은
 * ListAdapterViewType 의 viewType 을 참조 한다.
 * @param viewModel RecyclerView 에 사용되는 viewModel 을 설정 한다.
 *
 * ```kotlin
    binding.yourRecyclerView.adapter = BaseTypeListAdapter<YourTypeModel>(
        mapOf(
            YourTypeModel.T_TYPE to R.layout.item_type,
            YourTypeModel.T_ERROR to R.layout.item_error
        ),
        viewModel
    )
 * ```
 */
open class BaseTypeListAdapter<T: ListAdapterViewType<*>>(
    open val residMap: Map<Int, Int>,
    open var viewModel: ViewModel? = null,
    val viewDataBindingCallback: ((ViewDataBinding, Int) -> Unit)? = null
) : ListAdapter<T, BaseViewHolder<T>>(BaseDiffer<T>()) {
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
        val item = currentList[position]
        return if (item is ListAdapterViewType<*>) {
            item.viewType
        } else {
            super.getItemViewType(position)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        viewDataBindingCallback?.invoke(holder.binding, position)
        holder.bind(getItem(position), viewModel)
    }
}
