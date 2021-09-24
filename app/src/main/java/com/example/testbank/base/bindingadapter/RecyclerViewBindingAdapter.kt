package com.example.testbank.base.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omidio.tabsyncedrecyclerview.TabSyncedRecyclerView
import timber.log.Timber

// https://stackoverflow.com/questions/56857010/type-inference-failed-but-cannot-check-for-instance-of-erased-type

@BindingAdapter("bindItems", "scrollToTop", requireAll = false)
fun <T> RecyclerView.bindItems(items: List<T>?, scrollToTop: Boolean) {
    adapter?.let {
        when (it) {
            is ListAdapter<*, *> -> {
                val listAdapter = it as ListAdapter<T, RecyclerView.ViewHolder>
                listAdapter.submitList(items)

                if (scrollToTop) {
                    postDelayed({ // insert가 되었을때 0번째로 이동시켜준다.
                        smoothScrollToPosition(0)
                    }, 1000)
                }
            }

            else -> error("unknown adapter")
        }
    }
}

@BindingAdapter("bindItems", "scrollToTop", requireAll = false)
fun <T> RecyclerView.bindItems(items: PagedList<T>?, scrollToTop: Boolean) {
    adapter?.let {
        Timber.d("[STORE] BIND ITEM ${items?.size}")

        when (it) {
            is PagedListAdapter<*, *> -> {
                val listAdapter = it as PagedListAdapter<T, RecyclerView.ViewHolder>
                listAdapter.submitList(items)

                if (scrollToTop) {
                    postDelayed({ // insert가 되었을때 0번째로 이동시켜준다.
                        smoothScrollToPosition(0)
                    }, 1000)
                }
            }

            else -> error("unknown adapter")
        }
    }
}

@BindingAdapter("bindSmoothScrollToPosition")
fun RecyclerView.bindSmoothScrollToPosition(position: Int) {
    smoothScrollToPosition(position)
}

// https://github.com/crocsandcoffee/tab-synced-recycler-view
@BindingAdapter("bindTabIndex")
fun TabSyncedRecyclerView.bindTabIndex(indexes: List<Int>?) {
    indexes?.let {
        setCountItemsByTabIndex(it)
    }
}