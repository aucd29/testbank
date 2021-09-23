package com.example.testbank.repository

import com.example.testbank.repository.dummy.HiltDummyRepository
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import com.example.testbank.repository.local.model.more.BaseMoreModel
import com.example.testbank.repository.remote.dto.HiltRemoteRepository
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

}