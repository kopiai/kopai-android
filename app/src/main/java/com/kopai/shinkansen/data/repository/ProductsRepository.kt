package com.kopai.shinkansen.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.google.gson.Gson
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.local.ProductsRemoteMediator
import com.kopai.shinkansen.data.local.room.ProductsDatabase
import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.LoginResponse
import com.kopai.shinkansen.data.remote.response.ProductsItem
import com.kopai.shinkansen.data.remote.response.ProductsResponse
import com.kopai.shinkansen.data.remote.response.StoriesResponse
import com.kopai.shinkansen.data.remote.response.StoryItem
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class ProductsRepository constructor(
    private val productsDatabase: ProductsDatabase,
    private val apiService: ApiService,
) {

    fun register(
        name: String,
        email: String,
        password: String,
    ): LiveData<ResultState<ErrorMessageResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.register(name, email, password)
                Log.d(TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorMessageResponse::class.java)
                emit(ResultState.Error(errorBody.message ?: "Server error"))
            } catch (e: Exception) {
                Log.d(TAG, "register: ${e.message}")
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
                Log.d(TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorMessageResponse::class.java)
                emit(ResultState.Error(errorBody.message ?: "Server error"))
            } catch (e: Exception) {
                Log.d(TAG, "login: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    fun getProductsWithLocation(): LiveData<ResultState<StoriesResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.getStories(null, null, 1)
                Log.d(TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                Log.d(TAG, "login: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    fun getProductsBeen(): LiveData<ResultState<ProductsResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.getProductsBeen(true)
                Log.d(TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                Log.d(TAG, "login: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    fun getProductsPaging(): LiveData<PagingData<ProductsItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config =
                PagingConfig(
                    pageSize = 2,
                ),
            remoteMediator = ProductsRemoteMediator(productsDatabase, apiService),
            pagingSourceFactory = {
                productsDatabase.productDao().getAllProducts()
            },
        ).liveData
    }

    fun uploadProduct(
        imageProduct: File,
        description: String,
    ) = liveData {
        emit(ResultState.Loading)
        val requestBody = description.toRequestBody("text/plain".toMediaType())
        val requestImageFile = imageProduct.asRequestBody("image/jpeg".toMediaType())
        val multipartBody =
            MultipartBody.Part.createFormData(
                "photo",
                imageProduct.name,
                requestImageFile,
            )
        try {
            val successResponse = apiService.uploadStories(multipartBody, requestBody)
            emit(ResultState.Success(successResponse))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ErrorMessageResponse::class.java)
            emit(ResultState.Error(errorResponse.message ?: "Server error"))
        }
    }


    companion object {
        const val TAG = "ProductsRepository"
    }
}
