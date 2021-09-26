package com.example.testbank.base.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.testbank.base.extension.makeCircularList
import timber.log.Timber

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

    var isScrolled = false
    var tempPosition = -1
    var viewpager2: ViewPager2? = null

    /** 실제 어뎁터에 등록되어 있는 리스트의 개수 */
    val realCount: Int
        get() = if (currentList.size == 0) {
            0
        } else {
            currentList.size - INCREASE_COUNT
        }

    override fun submitList(list: MutableList<T>?) {
        super.submitList(list?.makeCircularList())
    }

    fun setViewPager2(viewpager: ViewPager2, scrollCallback: ((Int) -> Unit)? = null) {
        viewpager2 = viewpager

        val pageListener = object: ViewPager2.OnPageChangeCallback()  {
            override fun onPageScrollStateChanged(state: Int) {
                when (state) {
                    ViewPager2.SCROLL_STATE_DRAGGING,
                    ViewPager2.SCROLL_STATE_SETTLING -> {
                        isScrolled = true
                    }
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        isScrolled = false

                        Timber.d("[SERVICE] IDLE TEMP : $tempPosition")

                        if (tempPosition != -1) {
                            if (tempPosition == 0) {
                                Timber.d("[SERVICE] MOVE ${realCount - 1}")
                                viewpager.setCurrentItem(realCount, false)
                                scrollCallback?.invoke(realCount - 1)
                            } else if (tempPosition == itemCount - 1) {
                                Timber.d("[SERVICE] MOVE 0")
                                viewpager.setCurrentItem(1, false)
                                scrollCallback?.invoke(0)
                            } else {
                                Timber.e("[SERVICE] MOVE ${tempPosition - 1}")
                                scrollCallback?.invoke(tempPosition - 1)
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

    override fun onCurrentListChanged(previousList: MutableList<T>, currentList: MutableList<T>) {
        super.onCurrentListChanged(previousList, currentList)

        viewpager2?.let { vp ->
            if (previousList.size != currentList.size && !vp.isFakeDragging) {
                // isFakeDragging 아닐때만 실행시키도록 예외처리.
                Timber.d("[SERVICE] MOVE TO START POSITION $START_POSITION")
                vp.setCurrentItem(START_POSITION, false)
            }
        }
    }

    companion object {
        const val INCREASE_COUNT = 2
        const val START_POSITION = 1
    }
}