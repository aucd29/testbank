package com.example.testbank.repository.remote.dto

import com.example.testbank.repository.RepositoryInterface
import com.example.testbank.repository.local.model.alarm.BaseAlarmModel
import com.example.testbank.repository.local.model.more.BaseMoreModel
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HiltRemote

@Singleton
class RemoteRepository @Inject constructor(
    private val retrofit: Retrofit.Builder
) : RepositoryInterface {
    override fun alarmMenus(): Single<List<BaseAlarmModel>> =
        Single.just(emptyList())

    override fun moreMenus(): Single<List<BaseMoreModel>> =
        Single.just(emptyList())
}