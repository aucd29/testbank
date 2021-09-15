package com.example.testbank.base.webview

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import timber.log.Timber
import java.io.IOException
import java.net.CookieManager
import java.net.CookiePolicy
import java.net.CookieStore
import java.net.URI
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject

interface CookieProxyInterface : CookieJar {
}

class MyCookieProxy @Inject constructor(
    protected val cookieManager: CookieInterface
) : CookieManager(null, CookiePolicy.ACCEPT_ALL),
    CookieProxyInterface
{
    @Throws(IOException::class)
    override fun put(uri: URI?, responseHeaders: Map<String, List<String>>?) {
        // make sure our args are valid
        if (uri == null || responseHeaders == null) {
            return
        }

        // save our url once
        val url = uri.toString()

        // go over the headers
        for (headerKey in responseHeaders.keys) {
            // ignore headers which aren't cookie related
            if (!(headerKey.equals("Set-Cookie2", ignoreCase = true) ||
                        headerKey.equals("Set-Cookie", ignoreCase = true))) {
                continue
            }

            // process each of the headers
            responseHeaders[headerKey]?.forEach { headerValue ->
                cookieManager.setCookie(url, headerValue)
            }
        }
    }

    @Throws(IOException::class)
    override fun get(uri: URI?, requestHeaders: Map<String, List<String>>?): Map<String, List<String>> {
        // make sure our args are valid
        require(!(uri == null || requestHeaders == null)) { "Argument is null" }

        // save our url once
        val url = uri.toString()

        // prepare our response
        val res: MutableMap<String, List<String>> = HashMap()

        // get the cookie
        val cookie = cookieManager.getCookie(url)

        // return it
        if (cookie != null) {
            res["Cookie"] = listOf(cookie)
        }

        return res
    }

    override fun getCookieStore(): CookieStore {
        // we don't want anyone to work with this cookie store directly
        throw UnsupportedOperationException()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val generatedResponseHeaders = HashMap<String, List<String>>()
        val cookiesList = ArrayList<String>()
        for (c in cookies) {
            // toString correctly generates a normal cookie string
            cookiesList.add(c.toString())
        }
        generatedResponseHeaders["Set-Cookie"] = cookiesList
        try {
            put(url.toUri(), generatedResponseHeaders)
        } catch (e: IOException) {
            Timber.e("Error adding cookies through okhttp")
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookieArrayList = ArrayList<Cookie>()
        try {
            val cookieList = get(url.toUri(), HashMap())
            // Format here looks like: "Cookie":["cookie1=val1;cookie2=val2;"]
            for (ls in cookieList.values) {
                for (s in ls) {
                    val cookies = s.split(";".toRegex()).toTypedArray()
                    for (cookie in cookies) {
                        val c: Cookie? = Cookie.parse(url, cookie)
                        c?.let {
                            cookieArrayList.add(it)
                        }
                    }
                }
            }
        } catch (e: IOException) {
            Timber.e("error making cookie!")
        }

        return cookieArrayList
    }
}