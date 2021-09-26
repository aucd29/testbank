package com.example.testbank.repository.dummy

import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.local.model.alarm.AlarmDeposit
import com.example.testbank.repository.local.model.alarm.AlarmMenuModel
import com.example.testbank.repository.local.model.alarm.AlarmSubjectModel
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import com.example.testbank.repository.local.model.more.*
import com.example.testbank.repository.local.model.search.SearchResultModel
import com.example.testbank.repository.local.model.service.*
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltDummyRepository

@Singleton
class DummyRepository @Inject constructor(

) : RepositoryInterface {
    override fun serviceMenus(): Single<List<BaseServiceModel>> =
        Single.fromCallable {
            var index = 0

            mutableListOf(
                ServiceScrollBannerModel(
                    id = index++,
                    banner = listOf(
                        ServiceScrollBannerItem(
                            title = "중저 신용 고객 누구나",
                            subTitle = "26주 적금 이자 2배",
                            image = "",
                            backgroundColor = "#FF018786",
                            titleColor = "#FF9800"
                        ),
                        ServiceScrollBannerItem(
                            title = "내 신용 정보 첫 조회 이벤트",
                            subTitle = "내 신용정보명\n조회만 해도,\n스타벅스 1만명",
                            image = "",
                            backgroundColor = "#395bbf"
                        ),
                        ServiceScrollBannerItem(
                            title = "더 많은 중저신용자를 위해",
                            subTitle = "중신용대출\n중신용비삼금대출\n첫 달 이자 지원중",
                            image = "",
                            backgroundColor = "#3a5894"
                        ),
                    )
                ),

                ServiceSubjectModel(
                    id = index++,
                    title = "예금ㆍ적금"
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "입출금 통장",
                    subTitle = "까다로운 계좌개설도 손쉽게",
                    rate = "연 0.10%",
                    rateColor = "#FF018786"
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "모임 통",
                    subTitle = "함께쓰고 같이봐요",
                    rate = "연 0.10%",
                    rateColor = "#FF018786"
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "세이프 박스",
                    subTitle = "여주자금을 따로 보관하세요",
                    rate = "연 0.80%",
                    rateColor = "#FF018786"
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "저금통",
                    subTitle = "매일매일 조금씩 쌓여요",
                    rate = "연 2.00%",
                    rateColor = "#FF018786"
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "자유적금",
                    subTitle = "매일/매주 자유롭게",
                    rate = "연 1.80%",
                    rateBadge = "최고",
                    rateByDate = "12개월 기준",
                    rateColor = "#FF018786"
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "26주적금",
                    subTitle = "캐릭터와 함께 즐거운 도전",
                    rate = "연 2.80%",
                    rateBadge = "최고",
                    rateByDate = "6개월 기준",
                    rateColor = "#FF018786",
                    isLast = true
                ),
                ServiceBannerModel(
                    id = index++,
                    title = "모임통장",
                    subTitle = "내년에는 해외여행 함께가자",
                    image = "",
                    backgroundColor = "#8be4e4",
                    textColor = "#000000"
                ),


                ServiceSubjectModel(
                    id = index++,
                    title = "대출"
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "비상금대출",
                    subTitle = "현금 필요할 때 유용하게",
                    badge = "UP",
                    badgeTint = "#e54545",
                    rate = "연 3.80%",
                    rateBadge = "최저",
                    rateColor = "#395bbf",
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "마이너스 통장대출",
                    subTitle = "이자는 사용한 만큼만",
                    rate = "연 3.36%",
                    rateBadge = "최저",
                    rateColor = "#395bbf",
                ),
                ServiceMenu1Model(
                    id = index++,
                    title = "신용대출/중신용대출",
                    subTitle = "목돈이 필요할 땐 쉽고 빠르게",
                    badge = "UP",
                    badgeTint = "#e54545",
                    rate = "연 3.52%",
                    rateBadge = "최저",
                    rateColor = "#395bbf",
                    isLast = true,
                ),
                ServiceBannerModel(
                    id = index++,
                    title = "마이너스통장대출",
                    subTitle = "이자는 사용한 만큼만",
                    image = "",
                    backgroundColor = "#FF018786"
                ),


                ServiceSubjectModel(
                    id = index++,
                    title = "서비스"
                ),
                ServiceMenu2Model(
                    id = index++,
                    title = "내 신용정보",
                    subTitle = "내 신용정보를 안정하고 간편하게",
                ),
                ServiceMenu2Model(
                    id = index++,
                    title = "해외송금 보내기",
                    subTitle = "해외계좌송금과 WU빠른해외송금",
                ),
                ServiceMenu2Model(
                    id = index++,
                    title = "해외송금 받기",
                    subTitle = "지금방문 없이 간편하게",
                    isLast = true,
                ),
                ServiceBannerModel(
                    id = index++,
                    title = "체크카드",
                    subTitle = "평일엔 0.2% 주말엔 0.4% 혜택",
                    image = "",
                    backgroundColor = "#395bbf"
                ),


                ServiceSubjectModel(
                    id = index++,
                    title = "제휴서비스"
                ),
                ServiceMenu2Model(
                    id = index++,
                    title = "해외주식 투자",
                    subTitle = "한국투자증권에서 제공하는 미니스탁 서비스",
                ),
                ServiceMenu2Model(
                    id = index++,
                    title = "증권사 주식계좌",
                    subTitle = "간편하게 개설하는 중권사 계좌",
                ),
                ServiceMenu2Model(
                    id = index++,
                    title = "제휴 신용카드",
                    subTitle = "신청은 간편하게 혜택은 다양하게",
                    isLast = true,
                ),
                ServiceBannerModel(
                    id = index++,
                    title = "제휴신용카드",
                    subTitle = "나의 맞춤형 카드 혜택 찾기",
                    image = "",
                    backgroundColor = "#3a5894"
                ),
                ServiceSubjectModel(
                    id = index++,
                    title = "mini"
                ),
                ServiceMenu2Model(
                    id = index++,
                    title = "카카오뱅크 mini",
                    subTitle = "10대부터 만들고 용돈을 편하게",
                ),
                ServiceMenu2Model(
                    id = index++,
                    title = "mini카드",
                    subTitle = "결제도 ATM도 교통비도 카드 하나로",
                    isLast = true,
                ),
            )
        }

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
                        backgroundColor = "#ececec",
                        textColor = "#000000",
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

    override fun search(keyword: String, page: Int, endImage: Boolean, endVideo: Boolean): Single<SearchResultModel> =
        Single.just(SearchResultModel())
}