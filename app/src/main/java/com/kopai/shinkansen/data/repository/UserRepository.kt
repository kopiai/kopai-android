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
import com.kopai.shinkansen.data.remote.retrofit.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
    ): LiveData<ResultState<ErrorMessageResponse>> =
        liveData {
            emit(ResultState.Loading)
            try {
                val response = apiService.register(name, email, password)
                Log.d(ProductsRepository.TAG, response.toString())
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

//    fun editProfille
fun editProfile(
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

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        const val TAG = "UserRepository"
    }
}
