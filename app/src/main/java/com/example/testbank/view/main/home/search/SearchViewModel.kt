package com.example.testbank.view.main.home.search

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagedList
import com.example.testbank.base.BaseViewModel
import com.example.testbank.base.EventInterface
import com.example.testbank.deviceapi.dialog.DialogModel
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.repository.local.model.search.SearchPagedListManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val pagedListManager: SearchPagedListManager,
    private val string: SearchStringInterface,
    event: EventInterface
) : BaseViewModel(),
    EventInterface by event
{
    val keyword: MutableLiveData<String> = stateHandle.getLiveData(KEY_SEARCH_KEYWORD, "카카오뱅크")
    val searchItems = ObservableField<PagedList<SearchModel>>()
    val isSearchEmpty = ObservableBoolean(false)
    val isKakaoLabel = ObservableBoolean(true)
    val isLoading = ObservableBoolean(false)
    val editorAction = ObservableField<(String?) -> Boolean>()
    private var isFirstLoad = true

    init {
        editorAction.set {
            search()
            true
        }
    }

    fun search() {
        if (keyword.value.isNullOrEmpty()) {
            dialog(string.pleaseInsertKeyword())
            return
        }

        isKakaoLabel.set(false)
        isSearchEmpty.set(false)
        isLoading.set(true)

        sendEvent(EVENT_HIDE_KEYBOARD)
        Timber.d("[SEARCH] ${keyword.value}")
        stateHandle.set(KEY_SEARCH_KEYWORD, keyword.value)

        pagedListManager.disposable = disposable
        pagedListManager.requestParams = keyword.value ?: ""

        if (isFirstLoad) {
            isFirstLoad = false

            disposable += Observables.zip(
                    pagedListManager.pagedList(),
                    pagedListManager.dataLoadedSubject
                ) { list, count ->
                    isSearchEmpty.set(count < 1)
                    list
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("[SEARCH] RX PAGED LIST ${it.size}")

                    // 검색 목록 설정
                    searchItems.set(it)
                    isLoading.set(false)
                }, {
                    Timber.e(it)
                    isSearchEmpty.set(true)
                    dialog(string.networkError())
                })
        } else {
            pagedListManager.invalidate()
        }
    }

    override fun onCleared() {
        super.onCleared()
        pagedListManager.onCleard()
    }

    @VisibleForTesting
    fun testIsFirstLoading() =
        isFirstLoad

    companion object {
        const val KEY_SEARCH_KEYWORD = "key-search-keyword"

        const val EVENT_LIKE = 0xFF00
        const val EVENT_HIDE_KEYBOARD = 0XFF01
    }
}