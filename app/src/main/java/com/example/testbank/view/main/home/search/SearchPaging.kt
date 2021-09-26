package com.example.testbank.repository.local.model.search

import androidx.paging.PagedList
import com.example.testbank.base.paging2.PagedListDataSource
import com.example.testbank.base.paging2.PagedListManager
import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.remote.HiltRemoteRepository
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider


/**
 * 페이징을 위한 관리자
 *
 * @property dataSourceProvider 데이터 소스 제공자
 * @property config 페이징 속성 값
 */
class SearchPagedListManager @Inject constructor(
    private val dataSourceProvider: Provider<SearchDataSource>,
    private val config: PagedList.Config,
) : PagedListManager<String, SearchModel>() {
    override fun pagedListConfig(): PagedList.Config =
        config

    override fun dataSourceProvider(): PagedListDataSource<String, SearchModel> =
        dataSourceProvider.get()
}


/**
 * 페이징을 위한 데이터 소스
 *
 * @property remoteRepository 데이터 관리자
 */
class SearchDataSource @Inject constructor(
    @HiltRemoteRepository
    private val remoteRepository: RepositoryInterface,
) : PagedListDataSource<String, SearchModel>() {
    private var isImageEnd = false
    private var isVideoEnd = false
    private var pageCount = 0

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<SearchModel>
    ) {
        Timber.d("[SEARCH] LOAD INIT : $requestParams")
        // "meta":{"is_end":true,"pageable_count":10,"total_count":10}
        // "meta":{"is_end":false,"pageable_count":1850,"total_count":1850}
        pageCount = 1

        disposable += remoteRepository.search(requestParams ?: "", pageCount)
            .subscribe({
                if (it.list.isNullOrEmpty()) {
                    isLoadAfter = false
                    callback.onResult(emptyList())
                    dataLoadedSubject.onNext(0)
                } else {
                    val list = it.list
                    addLastNum(list.size)

                    if (it.isVideoEnd && it.isImageEnd) {
                        isLoadAfter = false
                    }

                    isImageEnd = it.isImageEnd
                    isVideoEnd = it.isVideoEnd

                    //checkMoreDataSource(params.requestedLoadSize, list.size)
                    callback.onResult(list, 0, list.size)
                    dataLoadedSubject.onNext(list.size)
                }
            }, {
                isLoadAfter = false
                Timber.e(it)
                callback.onResult(listOf())
                dataLoadedSubject.onNext(0)
            })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<SearchModel>
    ) {
        if (isLoadAfter.not()) {
            callback.onResult(emptyList())
            Timber.d("[SEARCH] LOAD END")
            return
        }

        pageCount++
        Timber.d("[SEARCH] LOAD AFTER '$requestParams' = ${lastNumber.get()}, , PAGE: $pageCount")

        disposable += remoteRepository.search(requestParams ?: "", pageCount, isImageEnd, isVideoEnd)
            .subscribe({
                if (it.list.isNullOrEmpty()) {
                    isLoadAfter = false
                    callback.onResult(emptyList())
                } else {
                    val list = it.list
                    addLastNum(list.size)

                    if (it.isVideoEnd && it.isImageEnd) {
                        isLoadAfter = false
                    }

                    isImageEnd = it.isImageEnd
                    isVideoEnd = it.isVideoEnd

                    // checkMoreDataSource(params.requestedLoadSize, list.size)
                    callback.onResult(list)
                }
            }, {
                isLoadAfter = false
                Timber.e(it)
                callback.onResult(emptyList())
            })
    }

    private fun params(): String? =
        requestParams
}

