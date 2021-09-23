package com.example.testbank.view.main.home.search

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface HomeFragmentStringInterface {
    fun tabLabel(position: Int): String
}

@Singleton
class HomeFragmentString @Inject constructor(
    @ApplicationContext private val context: Context
) : HomeFragmentStringInterface {
    override fun tabLabel(position: Int): String {
        val id = context.resources.getIdentifier("home_tab_menu_$position", "string", context.packageName)
        return if (id != 0) {
            context.getString(id)
        } else {
            ""
        }
    }
}