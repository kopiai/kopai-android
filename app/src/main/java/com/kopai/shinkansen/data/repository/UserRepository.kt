package com.kopai.shinkansen.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.local.pref.UserPreference
import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.LoginResponse
import com.kopai.shinkansen.data.remote.response.RegisterResponse
import com.kopai.shinkansen.data.remote.response.UpdateProfileResponse
import com.kopai.shinkansen.data.remote.response.UpdateResult
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class UserRepository constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    ) {
//    suspend fun saveSession(user: UserPrefModel) {
//        userPreference.saveSession(user)
//    }

    fun getSession(): Flow<UserPrefModel> {
        return userPreference.getSession()
    }

    fun register(
        name: String,
        email: String,
        password: String,
    ): LiveData<ResultState<RegisterResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.register(name, email, password)
                Log.d(StoriesRepository.TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorMessageResponse::class.java)
                emit(ResultState.Error(errorBody.message ?: "Server error"))
            } catch (e: Exception) {
                Log.d(StoriesRepository.TAG, "register: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    fun login(
        email: String,
        password: String,
    ): LiveData<ResultState<LoginResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.login(email, password)
                Log.d(StoriesRepository.TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorMessageResponse::class.java)
                emit(ResultState.Error(errorBody.message ?: "Server error"))
            } catch (e: Exception) {
                Log.d(StoriesRepository.TAG, "login: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    suspend fun logout() {
        userPreference.logout()
    }

    //    fun editProfille

    fun uploadStory(
        imageStory: File,
        description: String,
    ) = liveData {
        emit(ResultState.Loading)
        val requestBody = description.toRequestBody("text/plain".toMediaType())
        val requestImageFile = imageStory.asRequestBody("image/jpeg".toMediaType())
        val multipartBody =
            MultipartBody.Part.createFormData(
                "photo",
                imageStory.name,
                requestImageFile,
            )
        try {
            val successResponse = apiService.uploadStory(multipartBody, requestBody)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ErrorMessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message ?: "Server error"))
        }
    }
    fun updateProfile(
        userId: Int?,
        name: String?,
        gender: String?,
        birth: String?,
        email: String?,
        phone: String?,
        address: String?,
        photo: File?,
    ) = liveData {
        emit(ResultState.Loading)
        val requestName = name!!.toRequestBody("text/plain".toMediaType())
        val requestGender = gender!!.toRequestBody("text/plain".toMediaType())
        val requestBirth = birth!!.toRequestBody("text/plain".toMediaType())
        val requestEmail = email!!.toRequestBody("text/plain".toMediaType())
        val requestPhone = phone!!.toRequestBody("text/plain".toMediaType())
        val requestAddress = address!!.toRequestBody("text/plain".toMediaType())

        val requestImageFile = photo!!.asRequestBody("image/jpeg".toMediaType())
        val multipartBody =
            MultipartBody.Part.createFormData(
                "photo",
                photo!!.name,
                requestImageFile,
            )
        try {
            val successResponse = apiService.updateProfile(
                userId!!.toInt(),
                requestName,
                requestGender,
                requestBirth,
                requestEmail,
                requestPhone,
                requestAddress,
                multipartBody
            )
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ErrorMessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message ?: "Server error"))
        }
    }
}
