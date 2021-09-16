package com.example.testbank.view.main.alarm

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.testbank.R
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.databinding.ItemAlarmMenuBinding
import com.example.testbank.databinding.ItemAlarmMenuMoreBinding
import com.example.testbank.repository.local.model.alarm.AlarmDeposit
import com.example.testbank.repository.local.model.alarm.AlarmMenuModel
import com.example.testbank.repository.local.model.alarm.AlarmSubjectModel
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import com.example.testbank.repository.local.model.more.BaseMoreModel
import com.example.testbank.view.main.more.HiltMoreFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import io.reactivex.Single
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltAlarmFragment

@Module
@InstallIn(FragmentComponent::class)
object AlarmFragmentModule {
    @HiltAlarmFragment
    @Provides
    fun provideMoreAdapter(): BaseTypeListAdapter<BaseAlarmModel> =
        BaseTypeListAdapter(
            mapOf(
                BaseAlarmModel.TYPE_SUBJECT to R.layout.item_alarm_subject,
                BaseAlarmModel.TYPE_MENU to R.layout.item_alarm_menu,
            ),
            null
        ) { binding, _, item ->
            when (binding) {
                is ItemAlarmMenuBinding -> {
                    val model = item as AlarmMenuModel
                    val mores = model.mores

                    mores?.forEach {
                        val context = binding.itemAlarmMenuLinearContainer.context
                        val inflater = LayoutInflater.from(context)
                        val bindingMore = DataBindingUtil.inflate<ItemAlarmMenuMoreBinding>(
                            inflater,
                            R.layout.item_alarm_menu_more,
                            null,
                            false
                        ).apply {
                            description = "${it.type} ${it.value} | ${it.name}"
                            date = it.date
                        }
                        binding.itemAlarmMenuLinearContainer.addView(bindingMore.root)
                    }
                }
            }
        }
}

@Module
@InstallIn(ViewModelComponent::class)
object AlarmViewModelModule {
    @HiltAlarmFragment
    @Provides
    fun provideAlarmList(): Single<List<BaseAlarmModel>> =
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
            )
        }
}