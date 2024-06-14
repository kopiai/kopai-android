package com.kopai.shinkansen.data.remote.retrofit

import com.kopai.shinkansen.data.local.pref.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor
    @Inject
    constructor(
        private val preference: UserPreference,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token =
                runBlocking {
                    preference.getSession().first()
                }.token
            val request = chain.request().newBuilder()
            request.addHeader("Authorization", "Bearer $token")
            return chain.proceed(request.build())
        }
    }
