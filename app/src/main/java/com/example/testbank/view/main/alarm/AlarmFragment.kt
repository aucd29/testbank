package com.example.testbank.view.main.alarm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.databinding.FragmentAlarmBinding
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmFragment : BaseFragment<FragmentAlarmBinding>(R.layout.fragment_alarm) {
    private val viewmodel: AlarmViewModel by viewModels()

    @Inject @HiltAlarmFragment
    lateinit var alarmAdapter: dagger.Lazy<BaseTypeListAdapter<BaseAlarmModel>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = viewmodel

            alarmRecycler.apply {
                itemAnimator = null
                adapter = alarmAdapter.get().apply {
                    viewModel = viewmodel
                }
            }
        }

        viewmodel.init()
    }
}