package com.example.testbank.repository.local.model.search

import androidx.databinding.ObservableBoolean
import com.example.testbank.base.adapter.ListAdapterViewType
import com.example.testbank.base.extension.isoStringToTodayString

class SearchModel(
    val id: Int,
    val thumbnailUrl: String,
    val displaySiteName: String,
    val datetime: String,
    val time: Long,
    val url: String,
    val like: ObservableBoolean = ObservableBoolean(false),
    override val viewType: Int = 0
) : ListAdapterViewType<Int> {
    override fun diff(): Int =
        id

    fun dateString(): String =
        datetime.isoStringToTodayString() ?: ""

    fun toggleLike() {
        like.set(like.get().not())
    }

    fun isLike(): Boolean =
        like.get()
}