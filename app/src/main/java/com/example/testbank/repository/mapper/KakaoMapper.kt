package com.example.testbank.repository.mapper

import com.example.testbank.base.extension.toTimeMills
import com.example.testbank.repository.local.model.search.SearchModel
import com.example.testbank.repository.remote.dto.KakaoImageResult
import com.example.testbank.repository.remote.dto.KakaoSearch
import com.example.testbank.repository.remote.dto.KakaoVClipResult

fun KakaoSearch<*>.toSearchModels(): List<SearchModel>? =
    this.documents?.mapIndexed { index, data ->
        when (data) {
            is KakaoImageResult ->
                SearchModel(
                    id = index,
                    thumbnailUrl = data.thumbnail_url ?: "",
                    displaySiteName = data.display_sitename ?: "",
                    datetime = data.datetime,
                    time = data.datetime.toTimeMills(),
                    url = data.doc_url ?: "",
                )

            is KakaoVClipResult ->
                SearchModel(
                    id = index,
                    thumbnailUrl = data.thumbnail,
                    displaySiteName = data.author,
                    datetime = data.datetime,
                    time = data.datetime.toTimeMills(),
                    url = data.url,
                )

            else ->
                error("unknown type")
        }
    }
