package com.example.testbank.repository.remote.dto

import java.io.Serializable


data class KakaoMetaResult (
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
) : Serializable

data class KakaoImageResult(
    val collection: String?,
    val thumbnail_url: String?,
    val image_url: String?,
    val width: Int,
    val height: Int,
    val display_sitename: String?,
    val doc_url: String?,
    val datetime: String
) : Serializable

data class KakaoVClipResult (
    val title: String,
    val url: String,
    val datetime: String,
    val play_time: Int,
    val thumbnail: String,
    val author: String
) : Serializable

data class KakaoSearch<T>(
    val documents: List<T>?,
    val meta: KakaoMetaResult?,

    // {"errorType":"InvalidArgument","message":"page is more than max"}
    val errorType: String? = null,
    val message: String? = null
) : Serializable

typealias KakaoImageSearchDto = KakaoSearch<KakaoImageResult>
typealias KakaoVClipSearchDto = KakaoSearch<KakaoVClipResult>