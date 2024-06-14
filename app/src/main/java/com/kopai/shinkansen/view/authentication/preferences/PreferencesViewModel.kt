package com.kopai.shinkansen.view.authentication.preferences

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel
    @Inject
    constructor(
        private val preferencesRepository: PreferencesRepository,
    ) : ViewModel() {
        fun getPreferences(userId: String) = preferencesRepository.getPreferences(userId)

        fun uploadPreferences(
            userId: String,
            preferences: String,
        ) = preferencesRepository.uploadPreferences(userId, preferences)
    }