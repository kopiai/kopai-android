package com.kopai.shinkansen.data.repository

import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.local.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository constructor(
    private val userPreference: UserPreference,
) {
    suspend fun saveSession(user: UserPrefModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserPrefModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }
}
