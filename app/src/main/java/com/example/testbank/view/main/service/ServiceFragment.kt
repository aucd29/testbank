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
import com.omidio.tabsyncedrecyclerview.LinearLayoutManagerWithSmoothScroller
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ServiceFragment : BaseFragment<FragmentServiceBinding>(R.layout.fragment_service) {
    private val viewmodel: ServiceViewModel by viewModels()

    @Inject @HiltServiceFragment
    lateinit var serviceAdapter: dagger.Lazy<BaseTypeListAdapter<BaseServiceModel>>
    @Inject @HiltServiceFragment
    lateinit var marginDecoration: dagger.Lazy<VerticalMarginItemDecoration>
    @Inject @HiltServiceFragment
    lateinit var linearLayoutManager: dagger.Lazy<LinearLayoutManagerWithSmoothScroller>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = viewmodel

            serviceRecycler.apply {
                addItemDecoration(marginDecoration.get())
                addOnScrollListener(TabSyncedScrollListener())
                layoutManager = linearLayoutManager.get()

                adapter = serviceAdapter.get().apply {
                    viewModel = viewmodel
                }

                setTabLayout(serviceTab)
            }
        }

        viewmodel.init()
    }
}