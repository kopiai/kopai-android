package com.kopai.shinkansen.data.remote.retrofit

import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.LoginResponse
import com.kopai.shinkansen.data.remote.response.NewsResponse
import com.kopai.shinkansen.data.remote.response.OrderItemResponse
import com.kopai.shinkansen.data.remote.response.PreferencesResponse
import com.kopai.shinkansen.data.remote.response.ProductsResponse
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //  Authentication
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): ErrorMessageResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @POST("users/update")
    suspend fun updateUser(
        @Field("userID") userID: Int,
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("address") address: String,
    ): ErrorMessageResponse

//   Stories

    @GET("news")
    suspend fun getNews(): NewsResponse

    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("location") location: Int = 0,
    ): ProductsResponse

    @POST("carts")
    suspend fun addToCart(
        @Field("userID") userID: Int,
        @Field("product_id") productID: Int,
    ): ErrorMessageResponse

    @POST("orders")
    suspend fun createOrder(
        @Field("userID") userID: Int,
        @Field("orderItems") orderItems: List<OrderItemResponse>,
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
