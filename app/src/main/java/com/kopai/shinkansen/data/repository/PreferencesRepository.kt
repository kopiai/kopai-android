package com.kopai.shinkansen.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.PreferencesResponse
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

class PreferencesRepository constructor(
    private val apiService: ApiService,
) {
    fun uploadPreferences(
        userId: String,
        effect: String,
        healthIssue: String,
        preferredAroma: String,
        preferredTaste: String,
    ) = liveData {
        emit(ResultState.Loading)
//        val requestUserId = userId.toRequestBody("text/plain".toMediaType())
//        val requestPreferences = preferences.toRequestBody("text/plain".toMediaType())
        try {
            val successResponse = apiService.uploadPreferences(userId.toInt(), effect, healthIssue, preferredAroma, preferredTaste)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ErrorMessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message ?: "Server error"))
        }
    }

    fun getPreferences(userId: String): LiveData<ResultState<PreferencesResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.getPreferencesById(userId.toInt())
                Log.d(PreferencesRepository.TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                Log.d(PreferencesRepository.TAG, "login: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    companion object {
        const val TAG = "PreferencesRepository"
    }
}
