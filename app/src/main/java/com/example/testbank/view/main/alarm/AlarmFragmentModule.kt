package com.example.testbank.view.main.alarm

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.testbank.R
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.databinding.ItemAlarmMenuBinding
import com.example.testbank.databinding.ItemAlarmMenuMoreBinding
import com.example.testbank.repository.local.model.alarm.AlarmMenuModel
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
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

                    binding.itemAlarmMenuLinearContainer.removeAllViews()

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
