package com.example.testbank.repository.dummy

import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.local.model.alarm.AlarmDeposit
import com.example.testbank.repository.local.model.alarm.AlarmMenuModel
import com.example.testbank.repository.local.model.alarm.AlarmSubjectModel
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import com.example.testbank.repository.local.model.more.*
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltDummy

@Singleton
class DummyRepository @Inject constructor(

) : RepositoryInterface {
    override fun alarmMenus(): Single<List<BaseAlarmModel>> =
        Single.fromCallable {
            var index = 0

            mutableListOf(
                AlarmSubjectModel(index++, "이번 주"),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_swap_horiz_24",
                    title = "누구의 통장(3333)",
                    description = "입금 44,333원 | 누구",
                    date = "9월 15일",
                    mores = listOf(
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일"),
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일")
                    ),
                ),

                AlarmSubjectModel(index++, "이번 알림"),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_email_24",
                    title = "간편이체 완료",
                    description = "최*안님이 44,333원을 받으셨습니다.",
                    date = "9월 12일",
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_email_24",
                    title = "간편이체 완료",
                    description = "최*안님이 14,333원을 받으셨습니다.",
                    date = "9월 12일",
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_swap_horiz_24",
                    title = "가족 모임 통장(5655)",
                    description = "출금 4,353원 | 간편이체(김이이)",
                    date = "9월 11일",
                    mores = listOf(
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일"),
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일")
                    ),
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_email_24",
                    title = "간편이체 완료",
                    description = "최*안님이 44,333원을 받으셨습니다.",
                    date = "9월 12일",
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_email_24",
                    title = "간편이체 완료",
                    description = "최*안님이 14,333원을 받으셨습니다.",
                    date = "9월 12일",
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_swap_horiz_24",
                    title = "가족 모임 통장(5655)",
                    description = "출금 4,353원 | 간편이체(김이이)",
                    date = "9월 11일",
                    mores = listOf(
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일"),
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일")
                    ),
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_email_24",
                    title = "간편이체 완료",
                    description = "최*안님이 44,333원을 받으셨습니다.",
                    date = "9월 12일",
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_email_24",
                    title = "간편이체 완료",
                    description = "최*안님이 14,333원을 받으셨습니다.",
                    date = "9월 12일",
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_swap_horiz_24",
                    title = "가족 모임 통장(5655)",
                    description = "출금 4,353원 | 간편이체(김이이)",
                    date = "9월 11일",
                    mores = listOf(
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일"),
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일")
                    ),
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_email_24",
                    title = "간편이체 완료",
                    description = "최*안님이 44,333원을 받으셨습니다.",
                    date = "9월 12일",
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_email_24",
                    title = "간편이체 완료",
                    description = "최*안님이 14,333원을 받으셨습니다.",
                    date = "9월 12일",
                ),
                AlarmMenuModel(index++,
                    icon = "ic_baseline_swap_horiz_24",
                    title = "가족 모임 통장(5655)",
                    description = "출금 4,353원 | 간편이체(김이이)",
                    date = "9월 11일",
                    mores = listOf(
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일"),
                        AlarmDeposit("출금", "24,303원", "일이오", "9월14일"),
                        AlarmDeposit("입금", "34,303원", "일이삼", "9월11일"),
                        AlarmDeposit("입금", "44,303원", "일이사", "9월10일")
                    ),
                ),
            )
        }

    override fun moreMenus(): Single<List<BaseMoreModel>> =
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