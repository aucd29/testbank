package com.example.testbank.view.main.more

import com.example.testbank.R
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.repository.local.model.more.*
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_BANNER
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_BAR
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_HEADER
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_MENU
import com.example.testbank.repository.local.model.more.BaseMoreModel.Companion.TYPE_SUBJECT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.Single
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltMoreFragment

@Module
@InstallIn(FragmentComponent::class)
object MoreFragmentModule {
    @HiltMoreFragment
    @Provides
    fun provideMoreAdapter(): BaseTypeListAdapter<BaseMoreModel> =
        BaseTypeListAdapter(
            mapOf(
                TYPE_HEADER to R.layout.item_more_header,
                TYPE_SUBJECT to R.layout.item_more_subject,
                TYPE_MENU to R.layout.item_more_menu,
                TYPE_BANNER to R.layout.item_more_banner,
                TYPE_BAR to R.layout.item_more_bar
            ),
            null
        )
}

@Module
@InstallIn(ViewModelComponent::class)
object MoreViewModelModule {
    @HiltMoreFragment
    @Provides
    fun provideMoreMenu(): Single<List<BaseMoreModel>> =
        Single.fromCallable {
            var index = 0
            val grayBackground = "#5EF2F4F6"

            mutableListOf(
                MoreHeaderModel(index++,
                    userName = "아무개",
                    banner = MoreHeaderEventBanner(
                        title = "제휴 신용카트 9월 이벤트",
                        backgroundColor = "#FF9800",
                        textColor = "#FFFFFF",
                        startIcon = null
                    )
                ),
                MoreBarModel(index++),

                MoreMenuModel(index++, "내 계좌"),
                MoreMenuModel(index++, "내 신용정보"),
                MoreMenuModel(index++, "휴면예금/보험금 찾기"),
                MoreMenuModel(index++, "국민지원금 신청", true),
                MoreBarModel(index++),

                MoreSubjectModel(index++, "이체/출금"),
                MoreMenuModel(index++, "이체"),
                MoreMenuModel(index++, "다건이체"),
                MoreMenuModel(index++, "자동이체"),
                MoreMenuModel(index++, "이체내역 조회"),
                MoreMenuModel(index++, "ATM 스마트출금"),
                MoreBarModel(index++),

                MoreSubjectModel(index++, "해외송금"),
                MoreMenuModel(index++, "해외송금 보내기"),
                MoreMenuModel(index++, "해외송금 받기"),
                MoreMenuModel(index++, "해외송금 내역조회"),
                MoreMenuModel(index++, "거래외국환은행 지정"),
                MoreBarModel(index++),

                MoreSubjectModel(index++, "카드"),
                MoreMenuModel(index++, "내 카드"),
                MoreMenuModel(index++, "카드 이용내역"),
                MoreMenuModel(index++, "지원금 이용내역", true),
                MoreMenuModel(index++, "카드 혜택안내"),
                MoreBarModel(index++),

                MoreSubjectModel(index++, "제휴서비스"),
                MoreMenuModel(index++, "해외주식 투자"),
                MoreMenuModel(index++, "증권사 주식계좌"),
                MoreMenuModel(index++, "제휴 신용카드"),
                MoreBarModel(index++),

                MoreSubjectModel(index++, "mini"),
                MoreMenuModel(index++, "카카오뱅크 mini"),
                MoreMenuModel(index++, "내 mini카드"),
                MoreMenuModel(index++, "mini 이용가이드"),
                MoreBarModel(index++, bottomBackgroundColor = grayBackground),

                MoreSubjectModel(index++, "상품가입", backgroundColor = grayBackground),
                MoreMenuModel(index++, "입출금통장", backgroundColor = grayBackground),
                MoreMenuModel(index++, "모임통장", backgroundColor = grayBackground),
                MoreMenuModel(index++, "세이프박스", backgroundColor = grayBackground),
                MoreMenuModel(index++, "저금통", backgroundColor = grayBackground),
                MoreMenuModel(index++, "정기예금", backgroundColor = grayBackground),
                MoreMenuModel(index++, "자유적금", backgroundColor = grayBackground),
                MoreMenuModel(index++, "26주적금", backgroundColor = grayBackground),
                MoreMenuModel(index++, "프렌즈 체크카드", backgroundColor = grayBackground),
                MoreBarModel(index++, topBackgroundColor = grayBackground, bottomBackgroundColor = grayBackground),

                MoreBannerModel(
                    index,
                    title = "증권사 주식계좌",
                    description = "정보입력없이 빠르고 간편하게",
                    textColor = "#ffffff",
                    cardBackgroundColor = "#00a2a2",
                    icon = "https://file.truefriend.com/Storage/main/main/1_1159.png",
                    backgroundColor = grayBackground
                )

            )
        }
}