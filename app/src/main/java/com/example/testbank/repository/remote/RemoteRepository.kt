package com.example.testbank.repository.remote

import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import com.example.testbank.repository.local.model.more.BaseMoreModel
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.repository.local.model.search.SearchResultModel
import com.example.testbank.repository.local.model.service.BaseServiceModel
import com.example.testbank.repository.mapper.toSearchModels
import com.example.testbank.repository.remote.dto.KakaoImageSearchDto
import com.example.testbank.repository.remote.dto.KakaoVClipSearchDto
import com.example.testbank.repository.remote.service.KakaoRestSearchService
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltRemoteRepository

@Singleton
class RemoteRepository @Inject constructor(
    private val searchService: KakaoRestSearchService
) : RepositoryInterface {
    override fun serviceMenus(): Single<List<BaseServiceModel>> =
        Single.just(emptyList())

    override fun alarmMenus(): Single<List<BaseAlarmModel>> =
        Single.just(emptyList())

    override fun moreMenus(): Single<List<BaseMoreModel>> =
        Single.just(emptyList())

    override fun search(keyword: String, page: Int, isImageEnd: Boolean, isVideoEnd: Boolean): Single<SearchResultModel> =
        Singles.zip(
            if (isImageEnd.not()) {
                searchService.image(query = keyword, page = page.toString())
            } else {
                Single.just(KakaoImageSearchDto(
                    errorType = "end",
                    message = "end"
                ))
            },
            if (isVideoEnd.not()) {
                searchService.vclip(query = keyword, page = page.toString())
            } else {
                Single.just(KakaoVClipSearchDto(
                    errorType = "end",
                    message = "end"
                ))
            }
        ) { image, video ->
            val images = image.toSearchModels()
            val videos = video.toSearchModels()
            val newList = arrayListOf<SearchModel>().apply {
                images?.let(this::addAll)
                videos?.let(this::addAll)
            }

            newList.sortByDescending { it.time }

            val result = SearchResultModel(
                image.meta?.is_end ?: true,
                video.meta?.is_end ?: true,
                newList
            )

            result
        }
}