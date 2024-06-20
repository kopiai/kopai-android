package com.kopai.shinkansen.data.remote.retrofit

import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.LoginResponse
import com.kopai.shinkansen.data.remote.response.PreferencesResponse
import com.kopai.shinkansen.data.remote.response.RegisterResponse
import com.kopai.shinkansen.data.remote.response.StoriesResponse
import com.kopai.shinkansen.data.remote.response.UpdateProfileResponse
import com.kopai.shinkansen.data.remote.response.UpdateResult
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

//  Authentication
    @FormUrlEncoded
    @POST("users/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @Multipart
    @POST("users/update/{user_id}")
    suspend fun updateProfile(
        @Path("user_id") userId: Int,
        @Part("name") name: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("birth") birth: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("address") address: RequestBody,
        @Part file: MultipartBody.Part,
    ): UpdateProfileResponse


//   Stories
    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("location") location: Int = 0,
    ): StoriesResponse

    @Multipart
    @POST("stories")
    suspend fun uploadStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): ErrorMessageResponse


//   Product


//   Preferences

    @Multipart
    @POST("preferences")
    suspend fun uploadPreferences(
        @Part("user_id") userId: RequestBody,
        @Part("preferences") preferences: RequestBody,
    ): ErrorMessageResponse

    @JvmSuppressWildcards
    @GET("preferences/{user_id}")
    suspend fun getPreferences(
        @Path("user_id") userId: String,
    ): PreferencesResponse

//   Blend


//   Order
}
