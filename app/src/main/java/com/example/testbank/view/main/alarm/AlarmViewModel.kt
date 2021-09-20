package com.example.testbank.view.main.alarm

import androidx.databinding.ObservableField
import com.example.testbank.base.BaseViewModel
import com.example.testbank.repository.RepositoryManager
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val repositoryManager: RepositoryManager
) : BaseViewModel() {
    val items = ObservableField<List<BaseAlarmModel>>()

    fun init() {
        disposable += repositoryManager.alarmMenus()
            .subscribe(items::set, Timber::e)
    }
}
