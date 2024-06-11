package com.kopai.shinkansen.di

import android.content.Context
import com.kopai.shinkansen.data.local.pref.UserPreference
import com.kopai.shinkansen.data.local.pref.dataStore
import com.kopai.shinkansen.data.local.room.StoriesDatabase
import com.kopai.shinkansen.data.remote.retrofit.ApiConfig
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import com.kopai.shinkansen.data.remote.retrofit.AuthInterceptor
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideUserPreferences(
        @ApplicationContext context: Context,
    ) = UserPreference(context.dataStore)

    @Singleton
    @Provides
    fun provideStoryDatabase(
        @ApplicationContext context: Context,
    ) = StoriesDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideAuthInterceptor(userPreference: UserPreference): AuthInterceptor = AuthInterceptor(userPreference)

    @Singleton
    @Provides
    fun provideApiService(authInterceptor: AuthInterceptor): ApiService {
        return ApiConfig.getApiService(authInterceptor)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userPreference: UserPreference): UserRepository {
        return UserRepository(userPreference)
    }

    @Singleton
    @Provides
    fun provideStoriesRepository(
        storiesDatabase: StoriesDatabase,
        apiService: ApiService,
    ): StoriesRepository {
        return StoriesRepository(storiesDatabase, apiService)
    }
}