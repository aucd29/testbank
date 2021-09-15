package com.example.testbank.base.webview

import android.webkit.CookieManager
import android.webkit.WebView
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface CookieInterface {
    fun init(view: WebView, accept: Boolean = true)
    fun setCookie(url: String, value: String)
    fun getCookie(url: String): String?
    fun removeSessionCookies()
    fun flush()
}

@Singleton
class MyCookieManager @Inject constructor(
    private val manager: CookieManager
) : CookieInterface {
    override fun init(view: WebView, accept: Boolean) {
        // https://archive.htrucci.com/582/android-5-0-api-21-%EC%9D%B4%EC%83%81-pgkcp%EB%AA%A8%EB%93%88-%EA%B2%B0%EC%A0%9C%EC%8B%9C-webview-session-%EC%98%A4%EB%A5%98/
        manager.apply {
            setAcceptCookie(true)
            setAcceptThirdPartyCookies(view, accept)
            flush()
        }
    }

    override fun setCookie(url: String, value: String) {
        manager.setCookie(url, value)
    }

    override fun getCookie(url: String): String? =
        manager.getCookie(url)

    override fun removeSessionCookies() {
        manager.apply {
            setAcceptCookie(true)
            removeSessionCookies {
                Timber.d("REMOVE SESSION COOKIE $it")
            }
            flush()
        }
    }

    override fun flush() {
        manager.flush()
    }
}