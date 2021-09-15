package com.example.testbank.view.main.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.databinding.FragmentHomeBinding
import com.example.testbank.databinding.FragmentServiceBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ServiceFragment : BaseFragment<FragmentServiceBinding>(R.layout.fragment_service) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}