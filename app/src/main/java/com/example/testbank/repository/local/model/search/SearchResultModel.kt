package com.example.testbank.repository.local.model.search

data class SearchResultModel(
    val isImageEnd: Boolean = true,
    val isVideoEnd: Boolean = true,
    val list: List<SearchModel>? = null
)
