package com.kopai.shinkansen.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(user: UserPrefModel) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = user.userId
            preferences[EMAIL_KEY] = user.email
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
            preferences[USER_PAYMENT] = user.payment
        }
    }

//    suspend fun savePreferences(pref: PreferencesModel) {
//        dataStore.edit { preferences ->
//            preferences[PREF_NAME] = pref.name
//            preferences[IS_PREFERENCES] = true
//        }
//    }

    fun getSession(): Flow<UserPrefModel> {
        return dataStore.data.map { preferences ->
            UserPrefModel(
                preferences[USER_ID_KEY] ?: "",
                preferences[EMAIL_KEY] ?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false,
                preferences[USER_PAYMENT] ?: "",
            )
        }
    }

//    fun getPreferences(): Flow<PreferencesModel> {
//        return dataStore.data.map { preferences ->
//            PreferencesModel(
//                preferences[PREF_NAME] ?: "",
//                preferences[IS_PREFERENCES] ?: false,
//            )
//        }
//    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val USER_ID_KEY = stringPreferencesKey("userId")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
        private val USER_PAYMENT = stringPreferencesKey("payment")

        private val PREF_NAME = stringPreferencesKey("name")
        private val IS_PREFERENCES = booleanPreferencesKey("isPreferences")
    }
}
