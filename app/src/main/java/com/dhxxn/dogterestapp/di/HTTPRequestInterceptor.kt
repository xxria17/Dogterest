package com.dhxxn.dogterestapp.di

import okhttp3.Interceptor
import okhttp3.Response

class HTTPRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val request = origin.newBuilder()
            .addHeader("Content-Type", "application/x-www-form-urlencoded")
            .addHeader("Accept", "*/*")
            .build()
        return chain.proceed(request)
    }
}