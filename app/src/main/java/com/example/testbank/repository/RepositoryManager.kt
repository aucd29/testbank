package com.example.testbank.repository

import com.example.testbank.repository.dummy.HiltDummyRepository
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import com.example.testbank.repository.local.model.more.BaseMoreModel
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.repository.local.model.search.SearchResultModel
import com.example.testbank.repository.remote.HiltRemoteRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltRepositoryManager

interface RepositoryInterface {
    /**
     * 알람 관련 메뉴 목록을 반환 한다.
     * @return 알람 메뉴 목록
     */
    fun alarmMenus(): Single<List<BaseAlarmModel>>

    /**
     * 더보기 관련 메뉴 목록을 반환 한다.
     * @return 더보기 메뉴 목록
     */
    fun moreMenus(): Single<List<BaseMoreModel>>

    /**
     * 이미지 + 동영상 검색
     * @param keyword 검색어
     * @param endImage 더 이상 이미지 검색이 불가한지 유/무
     * @param endVideo 더 이상 동영상 검색이 불가한지 유/무
     * @return 검색 결과
     */
    fun search(keyword: String, page: Int, endImage: Boolean = false, endVideo: Boolean = false): Single<SearchResultModel>
}

@Singleton
class RepositoryManager @Inject constructor(
    @HiltDummyRepository private val dummyRepository: RepositoryInterface,
    @HiltRemoteRepository private val remoteRepository: RepositoryInterface
) : RepositoryInterface {
    override fun alarmMenus(): Single<List<BaseAlarmModel>> =
        dummyRepository.alarmMenus()

    override fun moreMenus(): Single<List<BaseMoreModel>> =
        dummyRepository.moreMenus()

    override fun search(keyword: String, page: Int, endImage: Boolean, endVideo: Boolean): Single<SearchResultModel> =
        remoteRepository.search(keyword, page, endImage, endVideo)
}