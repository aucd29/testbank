package com.example.testbank.repository.remote.service

import com.example.testbank.repository.remote.dto.KakaoImageSearchDto
import com.example.testbank.repository.remote.dto.KakaoVClipSearchDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoRestSearchService {
    // size 기본 값은 80
    // https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image
    @GET("v2/search/image")
    fun image(
        @Query("query") query: String,
        @Query("page") page: String = "1",
        @Query("sort") sort: String = "recency",
        @Query("size") size: String = "30"
    ): Single<KakaoImageSearchDto>

    // https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video
    @GET("v2/search/vclip")
    fun vclip(
        @Query("query") query: String,
          @Query("page") page: String = "1",
          @Query("sort") sort: String = "recency",
          @Query("size") size: String = "30"
    ): Single<KakaoVClipSearchDto>
}