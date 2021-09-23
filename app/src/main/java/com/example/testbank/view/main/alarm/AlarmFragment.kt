package com.example.testbank.view.main.alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.databinding.FragmentAlarmBinding
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class AlarmFragment : BaseFragment<FragmentAlarmBinding>(R.layout.fragment_alarm) {
    private val viewmodel: AlarmViewModel by viewModels()

    @Inject @HiltAlarmFragment
    lateinit var adapter: BaseTypeListAdapter<BaseAlarmModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = viewmodel

            alarmRecycler.adapter = adapter.apply {
                viewModel = viewmodel
            }
        }

        viewmodel.init()
    }
}