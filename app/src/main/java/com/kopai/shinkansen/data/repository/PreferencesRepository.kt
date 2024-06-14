package com.kopai.shinkansen.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.PreferencesResponse
import com.kopai.shinkansen.data.remote.response.StoriesResponse
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class PreferencesRepository constructor(
    private val apiService: ApiService,
) {
    fun uploadPreferences(
        userId: String,
        preferences: String,
    ) = liveData {
        emit(ResultState.Loading)
        val requestUserId = userId.toRequestBody("text/plain".toMediaType())
        val requestPreferences = preferences.toRequestBody("text/plain".toMediaType())
        try {
            val successResponse = apiService.uploadPreferences(requestUserId, requestPreferences)
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
                val response = apiService.getPreferences(userId)
                Log.d(StoriesRepository.TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                Log.d(StoriesRepository.TAG, "login: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }
}