package com.example.testbank.repository.remote.dto

import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import com.example.testbank.repository.local.model.more.BaseMoreModel
import com.example.testbank.repository.remote.KakaoRestSearchService
import io.reactivex.Single
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
    override fun alarmMenus(): Single<List<BaseAlarmModel>> =
        Single.just(emptyList())

    override fun moreMenus(): Single<List<BaseMoreModel>> =
        Single.just(emptyList())
}