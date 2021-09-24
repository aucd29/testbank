package com.example.testbank.repository.local.model.service

import com.example.testbank.base.adapter.ListAdapterViewType

open class BaseServiceModel(
    var id: Int,
    override val viewType: Int
) : ListAdapterViewType<Int> {
    override fun diff(): Int =
        id

    companion object {
        const val TYPE_SUBJECT = 0
        const val TYPE_SCROLL_BANNER = 1
        const val TYPE_MENU1 = 2
        const val TYPE_MENU2 = 3
        const val TYPE_BANNER = 4
    }
}

class ServiceSubjectModel(
    id: Int,
    val title: String,
) : BaseServiceModel(id, TYPE_SUBJECT)

data class ServiceScrollBannerItem(
    val title: String,
    val subTitle: String,
    val image: String,
    val backgroundColor: String,
    val titleColor: String = "#ffffff",
    val subTitleColor: String = "#ffffff",
    override val viewType: Int = 0
)  : ListAdapterViewType<String> {
    override fun diff(): String =
        title + subTitle
}

class ServiceScrollBannerModel(
    id: Int,
    val banner: List<ServiceScrollBannerItem>
) : BaseServiceModel(id, TYPE_SCROLL_BANNER)

class ServiceMenu1Model(
    id: Int,
    val title: String,
    val subTitle: String,
    val badge: String? = null,
    val badgeTint: String? = null,
    val rate: String,
    val rateColor: String? = null,
    val rateByDate: String? = null,
    val rateBadge: String? = null,
    val isLast: Boolean = false,
    val badgeBackground: String? = null,
) : BaseServiceModel(id, TYPE_MENU1)

class ServiceMenu2Model(
    id: Int,
    val title: String,
    val subTitle: String,
    val isLast: Boolean = false,
) : BaseServiceModel(id, TYPE_MENU2)

class ServiceBannerModel(
    id: Int,
    val title: String,
    val subTitle: String,
    val image: String,
    val backgroundColor: String,
    val textColor: String = "#ffffff"
) : BaseServiceModel(id, TYPE_BANNER)