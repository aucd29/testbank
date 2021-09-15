package com.example.testbank.view.main.home

import android.os.Bundle
import android.view.View
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}