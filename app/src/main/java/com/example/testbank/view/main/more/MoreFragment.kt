package com.example.testbank.view.main.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.databinding.FragmentMoreBinding
import com.example.testbank.repository.local.model.more.BaseMoreModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding>(R.layout.fragment_more) {
    private val viewmodel: MoreViewModel by viewModels()

    @Inject @HiltMoreFragment
    lateinit var adapter: BaseTypeListAdapter<BaseMoreModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewmodel

        adapter.viewModel = viewmodel
        binding.moreRecycler.adapter = adapter

        viewmodel.init()
    }
}