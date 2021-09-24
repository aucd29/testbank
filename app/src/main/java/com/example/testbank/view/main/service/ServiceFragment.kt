package com.example.testbank.view.main.service

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.base.decoration.VerticalMarginItemDecoration
import com.example.testbank.databinding.FragmentServiceBinding
import com.example.testbank.repository.local.model.service.BaseServiceModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServiceFragment : BaseFragment<FragmentServiceBinding>(R.layout.fragment_service) {
    private val viewmodel: ServiceViewModel by viewModels()

    @Inject @HiltServiceFragment
    lateinit var adapter: BaseTypeListAdapter<BaseServiceModel>
    @Inject @HiltServiceFragment
    lateinit var marginDecoration: VerticalMarginItemDecoration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = viewmodel

            serviceRecycler.addItemDecoration(marginDecoration)
            serviceRecycler.adapter = adapter.apply {
                viewModel = viewmodel
            }
        }

        viewmodel.init()
    }
}