package com.example.testbank.view.main.home.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagedList
import com.example.testbank.base.BaseViewModelTest
import com.example.testbank.base.EventInterface
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.repository.local.model.search.SearchPagedListManager
import com.nhaarman.expect.expect
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Test

@Suppress("NonAsciiCharacters")
class TestSearchViewModel : BaseViewModelTest<SearchViewModel>() {
    override fun createViewModel(): SearchViewModel =
        SearchViewModel(savedStateHandle, pagedListManager, string, event)

    var savedStateHandle = mock<SavedStateHandle>()
    var pagedListManager = mock<SearchPagedListManager>()
    var string = mock<SearchStringInterface>()
    var event = mock<EventInterface>()

    @Test
    fun `초기데이터 확인`() {
        // given
        val KEYWORD = "카카오뱅크"
        savedStateHandle = mock {
            on { getLiveData(any(), any<String>()) } doReturn MutableLiveData(KEYWORD)
        }

        // then
        expect(viewmodel.keyword.value).toBe(KEYWORD)
        expect(viewmodel.isSearchEmpty.get()).toBe(false)
        expect(viewmodel.isKakaoLabel.get()).toBe(true)
        expect(viewmodel.isLoading.get()).toBe(false)
        expect(viewmodel.testIsFirstLoading()).toBe(true)
    }

    @Test
    fun `공백시 검색 오류`() {
        // given
        val ERROR_DIALOG_MESSAGE = "message"
        savedStateHandle = mock {
            on { getLiveData(any(), any<String>()) } doReturn MutableLiveData("")
        }
        string = mock {
            on { pleaseInsertKeyword() } doReturn ERROR_DIALOG_MESSAGE
        }

        // when
        viewmodel.search()

        // then
        expect(viewmodel.keyword.value.isNullOrEmpty()).toBe(true)
        verify(event) {
            1 * { dialog(any<String>()) }
        }
        expect(viewmodel.isSearchEmpty.get()).toBe(false)
        expect(viewmodel.isKakaoLabel.get()).toBe(true)
        expect(viewmodel.isLoading.get()).toBe(false)
        expect(viewmodel.testIsFirstLoading()).toBe(true)
    }

    @Test
    fun `카카오 키워드 검색 시 오류`() {
        // given
        val KEYWORD = "카카오"
        val ERROR_DIALOG_MESSAGE = "networkError"
        savedStateHandle = mock {
            on { getLiveData(any(), any<String>()) } doReturn MutableLiveData(KEYWORD)
        }
        string = mock {
            on { networkError() } doReturn ERROR_DIALOG_MESSAGE
        }
        pagedListManager = mock {
            on { pagedList() } doReturn Observable.error(Throwable())
            on { dataLoadedSubject } doReturn PublishSubject.create()
        }

        // when
        viewmodel.search()

        // then
        expect(viewmodel.keyword.value.isNullOrEmpty()).toBe(false)
        expect(viewmodel.isSearchEmpty.get()).toBe(true)
        expect(viewmodel.isKakaoLabel.get()).toBe(false)
        expect(viewmodel.isLoading.get()).toBe(true)
        verify(event) {
            1 * { sendEvent(SearchViewModel.EVENT_HIDE_KEYBOARD) }
        }
        expect(viewmodel.testIsFirstLoading()).toBe(false)
        verify(string) {
            1 * { networkError() }
        }
        verify(event) {
            1 * { dialog(ERROR_DIALOG_MESSAGE) }
        }
    }

    @Test
    fun `카카오 키워드 검색`() {
        // given
        val KEYWORD = "카카오"
        val ERROR_DIALOG_MESSAGE = "networkError"
        savedStateHandle = mock {
            on { getLiveData(any(), any<String>()) } doReturn MutableLiveData(KEYWORD)
        }
        string = mock {
            on { networkError() } doReturn ERROR_DIALOG_MESSAGE
        }
        val pagedList = mock<PagedList<SearchModel>>()
        val subject = PublishSubject.create<Int>()
        pagedListManager = mock {
            on { pagedList() } doReturn Observable.just(pagedList)
            on { dataLoadedSubject } doReturn subject
        }

        // when
        viewmodel.search()

        // then
        expect(viewmodel.keyword.value.isNullOrEmpty()).toBe(false)
        expect(viewmodel.isSearchEmpty.get()).toBe(false)
        expect(viewmodel.isKakaoLabel.get()).toBe(false)
        // expect(viewmodel.isLoading.get()).toBe(false)
        verify(event) {
            1 * { sendEvent(SearchViewModel.EVENT_HIDE_KEYBOARD) }
        }
        expect(viewmodel.testIsFirstLoading()).toBe(false)
        verify(string) {
            0 * { networkError() }
        }
        verify(event) {
            0 * { dialog(ERROR_DIALOG_MESSAGE) }
        }
    }
}