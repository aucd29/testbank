package com.example.testbank.repository.remote

import com.example.testbank.repository.remote.dto.KakaoImageSearchDto
import com.example.testbank.repository.remote.dto.KakaoVClipSearchDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoRestSearchService {
    // size 기본 값은 80
    // https://developer.kakao.com/docs/restapi/search#이미지-검색
    @GET("v2/search/image")
    fun image(
        @Query("query") query: String,
        @Query("page") page: String = "1",
        @Query("sort") sort: String = "accuracy",
        @Query("size") size: String = "30"
    ): Single<KakaoImageSearchDto>

    //https://developer.kakao.com/docs/restapi/search#동영상-검색
    @GET("v2/search/vclip")
    fun vclip(
        @Query("query") query: String,
          @Query("page") page: String = "1",
          @Query("sort") sort: String = "accuracy",
          @Query("size") size: String = "30"
    ): Single<KakaoVClipSearchDto>

}