package com.kopai.shinkansen.data.remote.retrofit

import com.kopai.shinkansen.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private val BASE_URL = BuildConfig.BASE_URL

        fun getApiService(authInterceptor: AuthInterceptor): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                }
            val client =
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(authInterceptor)
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}