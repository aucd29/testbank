package com.example.testbank.base.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.testbank.base.extension.makeCircularList

class InfiniteTypeListAdapter<T: ListAdapterViewType<*>>(
    residMap: Map<Int, Int>,
    viewModel: ViewModel? = null,
    viewDataBindingCallback: ((ViewDataBinding, Int, T) -> Unit)? = null
) : BaseTypeListAdapter<T>(
    residMap,
    viewModel,
    viewDataBindingCallback
) {
    constructor(
        @LayoutRes resid: Int,
        viewModel: ViewModel? = null,
        viewDataBindingCallback: ((ViewDataBinding, Int, T) -> Unit)? = null
    ) : this(mapOf(0 to resid), viewModel, viewDataBindingCallback)

    val increaseCount = 2
    val startPosition = 1
    var isScrolled = false
    var tempPosition = -1

    /** 실제 어뎁터에 등록되어 있는 리스트의 개수 */
    val realCount: Int
        get() = if (currentList.size == 0) {
            0
        } else {
            currentList.size - increaseCount
        }

    override fun submitList(list: MutableList<T>?) {
        super.submitList(list?.makeCircularList())
    }

    fun setViewPager2(viewpager: ViewPager2, scrollCallback: ((Int) -> Unit)? = null) {
        val pageListener = object: ViewPager2.OnPageChangeCallback()  {
            override fun onPageScrollStateChanged(state: Int) {
                when (state) {
                    ViewPager2.SCROLL_STATE_DRAGGING,
                    ViewPager2.SCROLL_STATE_SETTLING -> {
                        isScrolled = true
                    }
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        isScrolled = false

                        if (tempPosition != -1) {
                            if (tempPosition == 0) {
                                viewpager.setCurrentItem(realCount, false)
                                scrollCallback?.invoke(0)
                            } else if (tempPosition == itemCount - 1) {
                                viewpager.setCurrentItem(1, false)
                                scrollCallback?.invoke(1)
                            } else {
                                scrollCallback?.invoke(tempPosition % realCount)
                            }
                        }
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (isScrolled) {
                    tempPosition = position
                }
            }
        }

        viewpager.registerOnPageChangeCallback(pageListener)
    }
}