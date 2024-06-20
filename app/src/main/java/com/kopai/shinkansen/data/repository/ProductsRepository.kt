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
import com.kopai.shinkansen.data.local.pref.UserPreference
import com.kopai.shinkansen.data.local.room.ProductsDatabase
import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.NewsResponse
import com.kopai.shinkansen.data.remote.response.OrderItemResponse
import com.kopai.shinkansen.data.remote.response.OrderResponse
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.data.remote.response.ProductsResponse
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.first
import retrofit2.HttpException

class ProductsRepository constructor(
    private val productsDatabase: ProductsDatabase,
    private val apiService: ApiService,
    private val userPreference: UserPreference,
) {
    fun getProductsWithLocation(): LiveData<ResultState<ProductsResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.getProducts(null, null, 1)
                Log.d(TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                Log.d(TAG, "login: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

    fun getProductsPaging(): LiveData<PagingData<ProductItem>> {
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

    fun getNews(): LiveData<ResultState<NewsResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.getNews()
                Log.d(TAG, response.toString())
                emit(ResultState.Success(response))
            } catch (e: Exception) {
                Log.d(TAG, "login: ${e.message}")
                emit(ResultState.Error(e.message.toString()))
            }
        }

//    fun addOrderItem(orderItems: List<OrderItemResponse>) =
//        liveData<ResultState<ErrorMessageResponse>> {
//            emit(ResultState.Loading)
//            val userID = userPreference.getSession().first().userId.toInt()
//            try {
//                val successResponse = apiService.createOrder(OrderResponse(userID, orderItems))
//                emit(ResultState.Success(successResponse))
//            } catch (e: HttpException) {
//                val errorBody = e.response()?.errorBody()?.string()
//                val errorResponse = Gson().fromJson(errorBody, ErrorMessageResponse::class.java)
//                emit(ResultState.Error(errorResponse.message ?: "Server error"))
//            }
//        }

    fun createOrder(orderItems: List<OrderItemResponse>) =
        liveData<ResultState<ErrorMessageResponse>> {
            emit(ResultState.Loading)
            val userID = userPreference.getSession().first().userId.toIntOrNull() ?: 1
            try {
                val successResponse = apiService.createOrder(OrderResponse(userID, orderItems))
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
