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

    /**
     * 좋아요를 선택한 항목에 대해서 상태 값을 조정한 뒤 리스트에 넣는다.
     * @param model 검색된 객체
     */
    fun toggleLike(model: SearchModel) {
        _likeItems.value?.let {
            _likeItems.value = it.toMutableList().apply {
                model.toggleLike()
                if (model.isLike()) {
                    add(model)
                } else {
                    remove(model)
                }

                Timber.d("[MAIN] TOGGLE $model, ${_likeItems.value?.size}")
            }
        }
    }
}