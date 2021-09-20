package com.example.testbank.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testbank.R
import com.example.testbank.base.BaseActivity
import com.example.testbank.databinding.ActivityMainBinding
import com.example.testbank.deviceapi.user.UserViewModel
import com.example.testbank.view.main.alarm.AlarmFragment
import com.example.testbank.view.main.home.HomeFragment
import com.example.testbank.view.main.more.MoreFragment
import com.example.testbank.view.main.service.ServiceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initNavController()
    }

    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                when (fragment) {
                    is HomeFragment,
                    is ServiceFragment,
                    is AlarmFragment,
                    is MoreFragment ->
                        finish()

                    else ->
                        super.onBackPressed()
                }
            }
        }
    }

    private fun initViewModel() {
        userViewModel.init()
    }

    private fun initNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()

        binding.bnvMain.setupWithNavController(navController)
    }

}