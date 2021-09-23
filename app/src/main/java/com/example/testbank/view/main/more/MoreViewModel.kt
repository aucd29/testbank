package com.example.testbank.view.main.more

import androidx.databinding.ObservableField
import com.example.testbank.base.BaseViewModel
import com.example.testbank.repository.HiltRepositoryManager
import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.local.model.more.BaseMoreModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    @HiltRepositoryManager
    private val repositoryManager: RepositoryInterface
) : BaseViewModel() {
    val items = ObservableField<List<BaseMoreModel>>()

    fun init() {
        disposable += repositoryManager.moreMenus()
            .subscribe(items::set, Timber::e)
    }
}