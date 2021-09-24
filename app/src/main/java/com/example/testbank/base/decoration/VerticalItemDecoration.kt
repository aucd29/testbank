package com.example.testbank.base.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class MarginItemDecoration: RecyclerView.ItemDecoration() {
    protected fun isFirstPosition(parent: RecyclerView, view: View) = parent.getChildAdapterPosition(view) == 0
    protected fun isLastPosition(parent:RecyclerView, view: View): Boolean {
        val current = parent.getChildAdapterPosition(view)
        val last = (parent.adapter?.itemCount ?: 0) - 1
        return current == last
    }
}

class VerticalMarginItemDecoration(
    private val spaceTop: Int = 0,
    private val spaceLeft: Int = 0,
    private val spaceRight: Int = 0,
    private val spaceBottom: Int = 0,
    private val lastSpaceBottom: Int = 0
) : MarginItemDecoration() {
    constructor(spaceHeight: Int): this(spaceHeight, spaceHeight, spaceHeight, spaceHeight, spaceHeight)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (isFirstPosition(parent, view)) {
                top = spaceTop
            }
            left   = spaceLeft
            right  = spaceRight
            bottom = if (isLasttPosition(parent, view)) lastSpaceBottom else spaceBottom
        }
    }

    private fun isLasttPosition(parent: RecyclerView, view: View) = with(parent) {
        adapter?.itemCount?.let {
            getChildAdapterPosition(view) == it - 1
        } ?: false
    }
}