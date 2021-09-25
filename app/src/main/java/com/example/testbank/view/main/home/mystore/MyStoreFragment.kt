package com.example.testbank.view.main.home.mystore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.base.adapter.BaseTypeListAdapter
import com.example.testbank.databinding.FragmentMystoreBinding
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.view.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyStoreFragment : BaseFragment<FragmentMystoreBinding>(R.layout.fragment_mystore) {
    private val mainViewModel: MainViewModel by activityViewModels()

    @Inject @HiltMyStoreFragment
    lateinit var mystoreAdapter: dagger.Lazy<BaseTypeListAdapter<SearchModel>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = mainViewModel

            mystoreRecycler.apply {
                itemAnimator = null
                adapter = mystoreAdapter.get().apply {
                    viewModel = mainViewModel
                }
            }
        }
    }
}