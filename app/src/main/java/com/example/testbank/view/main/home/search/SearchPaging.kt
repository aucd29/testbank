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

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<SearchModel>
    ) {
        Timber.d("[SEARCH] LOAD INIT : $requestParams")

        disposable += remoteRepository.search(requestParams ?: "", 1)
            .subscribe({
                if (it.list.isNullOrEmpty()) {
                    isLoadAfter = false
                    callback.onResult(listOf(), 0, 1)
                    dataLoadedSubject.onNext(0)
                } else {
                    isLoadAfter = it.isImageEnd && it.isVideoEnd
                    val list = it.list
                    addLastNum(list.size)

                    checkMoreDataSource(params.requestedLoadSize, list.size)
                    callback.onResult(list, 0, list.size)
                    dataLoadedSubject.onNext(list.size)
                }
            }, {
                Timber.e(it)
                callback.onResult(listOf(), 0, 1)
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

        Timber.d("[SEARCH] LOAD AFTER '$requestParams' = ${lastNumber.get()}, ${params.requestedLoadSize}")

        disposable += remoteRepository.search(requestParams ?: "", 0)
            .subscribe({
                it.list?.let { list ->
                    addLastNum(list.size)
                    checkMoreDataSource(params.requestedLoadSize, list.size)
                    callback.onResult(list)
                }
            }, {
                Timber.e(it)
                callback.onResult(emptyList())
            })
    }

    private fun params(): String? =
        requestParams
}

