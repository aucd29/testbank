package com.example.testbank.view.main.more

import androidx.databinding.ObservableField
import com.example.testbank.base.BaseViewModel
import com.example.testbank.repository.local.model.more.BaseMoreModel
import com.example.testbank.view.main.alarm.HiltAlarmFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    @HiltMoreFragment private val datas: Single<List<BaseMoreModel>>
) : BaseViewModel() {
    val items = ObservableField<List<BaseMoreModel>>()

    fun init() {
        disposable += datas.subscribe(items::set, Timber::e)
    }
}