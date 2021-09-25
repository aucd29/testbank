package com.example.testbank.base.dummy

import androidx.databinding.ObservableBoolean
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.repository.remote.dto.*

object Dummy {
    fun imageDto(isEnd: Boolean = false) = KakaoImageSearchDto(
        documents = listOf(KakaoImageResult(collection = "",
            thumbnail_url = "1",
            image_url = "imagurl",
            width = 10,
            height = 20,
            display_sitename = "display_sitename",
            doc_url = "doc_url",
            datetime = "2017-07-24T08:10:00.000+09:00"
        ), KakaoImageResult(collection = "",
            thumbnail_url = "2",
            image_url = null,
            width = 0,
            height = 0,
            display_sitename = null,
            doc_url = null,
            datetime = "2019-07-03T07:30:00.000+09:00"
        )),
        meta = KakaoMetaResult(
            is_end = isEnd,
            pageable_count = 1,
            total_count = 2
        )
    )

    fun vclipDto(isEnd: Boolean = false) = KakaoVClipSearchDto(
        documents = listOf(KakaoVClipResult(title = "",
            url = "url",
            datetime = "2021-08-27T17:15:36.000+09:00",
            play_time = 0,
            thumbnail = "3",
            author = "author"
        ), KakaoVClipResult(title = "",
            url = "",
            datetime = "2017-07-03T07:30:00.000+09:00",
            play_time = 0,
            thumbnail = "4",
            author = ""
        )),
        meta = KakaoMetaResult(
            is_end = isEnd,
            pageable_count = 1,
            total_count = 2
        )
    )

    fun searchModel(like: Boolean = false) = SearchModel(
        id = 0,
        thumbnailUrl = "",
        displaySiteName = "",
        datetime = "",
        time = 0,
        url = "",
        like = ObservableBoolean(like),
        viewType = 0
    )
}