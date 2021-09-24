package com.example.testbank.view.main.service

import androidx.databinding.ObservableField
import com.example.testbank.base.BaseViewModel
import com.example.testbank.repository.HiltRepositoryManager
import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.local.model.service.BaseServiceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    @HiltRepositoryManager
    private val repositoryManager: RepositoryInterface
) : BaseViewModel() {
    val items = ObservableField<List<BaseServiceModel>>()

    fun init() {
        disposable += repositoryManager.serviceMenus()
            .subscribe(items::set, Timber::e)
    }
}