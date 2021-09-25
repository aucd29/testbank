package com.example.testbank.view.main.home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.databinding.FragmentHomeBinding
import com.example.testbank.view.main.home.search.HomeFragmentStringInterface
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    @Inject @HiltHomeFragment
    lateinit var pagerAdapter: dagger.Lazy<FragmentStateAdapter>
    @Inject
    lateinit var string: dagger.Lazy<HomeFragmentStringInterface>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            homeViewpager.adapter = pagerAdapter.get()

            TabLayoutMediator(
                homeTabs,
                homeViewpager
            ) { tab, position -> tab.text = string.get().tabLabel(position) }.attach()
        }
    }
}