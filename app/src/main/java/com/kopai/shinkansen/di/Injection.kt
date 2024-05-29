package com.kopai.shinkansen.di

import android.content.Context
import com.kopai.shinkansen.data.pref.UserPreference
import com.kopai.shinkansen.data.pref.dataStore
import com.kopai.shinkansen.data.remote.retrofit.ApiConfig
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }

    fun provideStoriesRepository(context: Context): StoriesRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return StoriesRepository.getInstance(apiService)
    }
}
