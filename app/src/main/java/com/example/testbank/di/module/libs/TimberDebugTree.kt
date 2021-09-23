package com.example.testbank.di.module.libs

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimberDebugTree @Inject constructor(
) : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String =
        element.run {
            "[TT] ${fileName.replace(".kt|.java".toRegex(), "")}.$methodName($fileName:$lineNumber)"
        }
}