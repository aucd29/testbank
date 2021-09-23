package com.example.testbank.view.main.home.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.testbank.R
import com.example.testbank.base.BaseFragment
import com.example.testbank.base.adapter.BaseTypePagedListAdapter
import com.example.testbank.base.extension.hideKeyboard
import com.example.testbank.databinding.FragmentSearchBinding
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.view.main.MainViewModel
import com.example.testbank.view.main.home.search.SearchViewModel.Companion.EVENT_HIDE_KEYBOARD
import com.example.testbank.view.main.home.search.SearchViewModel.Companion.EVENT_LIKE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewmodel: SearchViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    @Inject @HiltSearchFragment
    lateinit var searchAdapter: dagger.Lazy<BaseTypePagedListAdapter<SearchModel>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            vm = viewmodel

            searchRecycler.adapter = searchAdapter.get().apply {
                viewModel = viewmodel
            }
        }

        observeLiveEvents(viewmodel)
    }

    override fun onLiveEvent(cmd: Int, data: Any?) {
        when (cmd) {
            EVENT_LIKE -> {
                val item = data as SearchModel
                mainViewModel.toggleLike(item)
            }

            EVENT_HIDE_KEYBOARD ->
                binding.root.hideKeyboard()
        }
    }
}