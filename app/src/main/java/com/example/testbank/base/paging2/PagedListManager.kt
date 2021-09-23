package com.example.testbank.base.paging2

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


/**
 * 페이저 관리를 위한 클래스
 *
 * @param PARAMS API 호출 시 옵션 관련 타입 정의
 * @param MODEL API 호출 결과 타입
 */
abstract class PagedListManager<PARAMS: Any, MODEL> {
    private lateinit var dataSource: PagedListDataSource<PARAMS, MODEL>

    lateinit var disposable: CompositeDisposable
    lateinit var requestParams: PARAMS

    var dataLoadedSubject: PublishSubject<Int> = PublishSubject.create()

    fun pagedList(): Observable<PagedList<MODEL>> =
        RxPagedListBuilder(dataSourceFactory(), pagedListConfig()).buildObservable()

    fun livePagedList(): LiveData<PagedList<MODEL>> =
        LivePagedListBuilder(dataSourceFactory(), pagedListConfig()).build()

    fun invalidate() {
        dataSource.invalidate()
    }

    private fun dataSourceFactory(): DataSource.Factory<Int, MODEL> {
        return object: DataSource.Factory<Int, MODEL>() {
            override fun create(): DataSource<Int, MODEL> {
                dataSource = dataSourceProvider()
                dataSource.dataLoadedSubject = dataLoadedSubject
                dataSource.requestParams = requestParams

                return dataSource
            }
        }
    }

    fun onCleard() {
        dataSource.onCleared()
    }

    /**
     * PagedList.Config 를 반환 한다.
     *
     * @return PagedList.Config 객체
     */
    abstract fun pagedListConfig(): PagedList.Config

    /**
     * PagedListDataSource 를 반환 한다.
     *
     * @return PagedListDataSource 객체
     */
    abstract fun dataSourceProvider(): PagedListDataSource<PARAMS, MODEL>
}