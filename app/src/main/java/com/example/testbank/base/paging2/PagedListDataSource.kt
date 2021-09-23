package com.example.testbank.base.paging2

import androidx.paging.ItemKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.atomic.AtomicInteger

/**
 * 데이터를 로드하기 위한 인터페이스
 *
 * @param T 데이터 타입
 * @constructor Create empty Base item keyed data source
 */
abstract class PagedListDataSource<PARAM, T>(
) : ItemKeyedDataSource<Int, T>() {
    protected var disposable: CompositeDisposable = CompositeDisposable()
    protected var lastNumber: AtomicInteger = AtomicInteger(0)
    protected var isLoadAfter = true

    lateinit var dataLoadedSubject: PublishSubject<Int>
    var requestParams: PARAM? = null

    override fun getKey(item: T): Int  =
        lastNumber.get()

    protected fun addLastNum(addNum: Int) {
        lastNumber.addAndGet(addNum)
    }

    protected fun checkMoreDataSource(requestedSize: Int, receivedSize: Int) {
        isLoadAfter = requestedSize == receivedSize
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<T>) {
    }

    fun onCleared() {
        disposable.dispose()
    }
}
