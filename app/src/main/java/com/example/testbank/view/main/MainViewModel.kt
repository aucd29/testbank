package com.example.testbank.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testbank.base.BaseViewModel
import com.example.testbank.repository.local.model.search.SearchModel
import timber.log.Timber

class MainViewModel : BaseViewModel() {
    private val _likeItems = MutableLiveData<MutableList<SearchModel>>(mutableListOf())
    val likeItems: LiveData<MutableList<SearchModel>>
        get() = _likeItems

    fun addLike(model: SearchModel) {
        _likeItems.value?.let {
            _likeItems.value = it.toMutableList().apply {
                model.toggleLike()
                add(model)
            }
        }

        Timber.d("[MAIN] ADD $model, ${_likeItems.value?.size}")
    }

    fun removeLike(model: SearchModel) {
        _likeItems.value?.let {
            _likeItems.value = it.toMutableList().apply {
                model.toggleLike()
                remove(model)
            }
        }

        Timber.d("[MAIN] REMOVE $model, ${_likeItems.value?.size}")
    }
}