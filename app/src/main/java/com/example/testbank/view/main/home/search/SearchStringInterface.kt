package com.example.testbank.view.main.home.search

import android.content.Context
import com.example.testbank.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface SearchStringInterface {
    fun pleaseInsertKeyword(): String
}

@Singleton
class SearchString @Inject constructor(
    @ApplicationContext private val context: Context
) : SearchStringInterface {
    override fun pleaseInsertKeyword(): String =
        context.getString(R.string.search_pls_insert_keyword)
}