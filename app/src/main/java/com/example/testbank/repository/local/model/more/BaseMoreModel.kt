package com.example.testbank.repository.local.model.more

import com.example.testbank.base.adapter.ListAdapterViewType

open class BaseMoreModel(
    var id: Int,
    override val viewType: Int
) : ListAdapterViewType<Int> {
    override fun diff(): Int =
        id

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_SUBJECT = 1
        const val TYPE_MENU = 2
        const val TYPE_BANNER = 3
        const val TYPE_BAR = 4
    }
}

class MoreHeaderModel(
    id: Int,
    val userName: String,
    val banner: MoreHeaderEventBanner? = null,
) : BaseMoreModel(id, TYPE_HEADER)

data class MoreHeaderEventBanner(
    val title: String,
    val link: String = "",
    val backgroundColor: String?,
    val textColor: String?,
    val startIcon: String?,
)

class MoreSubjectModel(
    id: Int,
    val title: String,
    val backgroundColor: String = "#ffffff",
) : BaseMoreModel(id, TYPE_SUBJECT)

class MoreMenuModel(
    id: Int,
    val title: String,
    val isBadge: Boolean = false,
    val badgeBackground: String? = null,
    val badgeText: String? = null,
    val badgeTextColor: String? = null,
    val backgroundColor: String = "#ffffff",
) : BaseMoreModel(id, TYPE_MENU)

class MoreBannerModel(
    id: Int,
    val title: String,
    val description: String,
    val textColor: String,
    var cardBackgroundColor: String,
    val icon: String,
    val backgroundColor: String = "#ffffff",
) : BaseMoreModel(id, TYPE_BANNER)

class MoreBarModel(
    id: Int,
    val topBackgroundColor: String? = null,
    val bottomBackgroundColor: String? = null
) : BaseMoreModel(id, TYPE_BAR)
