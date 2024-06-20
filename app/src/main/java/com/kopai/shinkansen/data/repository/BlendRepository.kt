package com.kopai.shinkansen.data.repository

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class BlendRepository constructor(
    private val apiService: ApiService
) {
    fun uploadBlend(
        coffeeIdOne: Int,
        coffeeIdTwo: Int,
        percentage: Int,
        totalWeight: Int,
        roastId: Int,
        grindId: Int,
        userId: Int,
        blendName: String,
        description: String,,
    ) = liveData {
        emit(ResultState.Loading)
        try {
            val successResponse = apiService.upload(multipartBody, requestBody)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ErrorMessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message ?: "Server error"))
        }
    }
}