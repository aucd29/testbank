package com.example.testbank.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testbank.R
import com.example.testbank.base.BaseActivity
import com.example.testbank.base.extension.heartBeat
import com.example.testbank.databinding.ActivityMainBinding
import com.example.testbank.deviceapi.user.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val userViewModel: UserViewModel by viewModels()

    @Inject @HiltMainActivity
    lateinit var mainAdapter: dagger.Lazy<FragmentStateAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            vpMain.adapter = mainAdapter.get()
            vpMain.offscreenPageLimit = mainAdapter.get().itemCount
            vpMain.isUserInputEnabled = false
            bnvMain.setOnItemSelectedListener {
                val position = when (it.itemId) {
                    R.id.home_fragment -> 0
                    R.id.service_fragment -> 1
                    R.id.alarm_fragment -> 2
                    else -> 3
                }

                bnvMain.heartBeat(position)
                vpMain.setCurrentItem(position, false)

                true
            }
        }

        userViewModel.init()
    }
}