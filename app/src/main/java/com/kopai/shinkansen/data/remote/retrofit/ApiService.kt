package com.kopai.shinkansen.data.remote.retrofit

import com.kopai.shinkansen.data.remote.response.BlendResponse
import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.LoginResponse
import com.kopai.shinkansen.data.remote.response.LoginResult
import com.kopai.shinkansen.data.remote.response.NewsResponse
import com.kopai.shinkansen.data.remote.response.OrderResponse
import com.kopai.shinkansen.data.remote.response.PreferencesResponse
import com.kopai.shinkansen.data.remote.response.ProductsResponse
import com.kopai.shinkansen.data.remote.response.RegisterResponse
import com.kopai.shinkansen.data.remote.response.UpdateProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
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

//  Product
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
    ): ProductsResponse

    @GET("products")
    suspend fun getProductsBeen(
        @Query("bean") bean: Boolean = true,
    ): ProductsResponse

//  Blend
    @FormUrlEncoded
    @POST("blends")
    suspend fun uploadBlend(
        @Field("coffee_id1") coffeIdOne: Int,
        @Field("coffee_id2") coffeeIdTwo: Int,
        @Field("percentage") percentage: Int,
        @Field("ukuran_gram") totalWeight: Int,
        @Field("roast_id") roastId: Int,
        @Field("grind_id") grindId: Int,
        @Field("user_id") userId: Int,
        @Field("blend_name") blendName: String,
        @Field("description") description: String,
    ): BlendResponse

//   Preferences
    @FormUrlEncoded
    @POST("preferences")
    suspend fun uploadPreferences(
        @Field("user_id") userId: Int,
        @Field("effect") effect: String,
        @Field("healthIssue") healthIssue: String,
        @Field("preferredAroma") preferredAroma: String,
        @Field("preferredTaste") preferredTaste: String,
    ): PreferencesResponse

    @JvmSuppressWildcards
    @GET("preferences")
    suspend fun getPreferences(
    ): PreferencesResponse

    @JvmSuppressWildcards
    @GET("preferences/{user_id}")
    suspend fun getPreferencesById(
        @Path("user_id") userId: Int,
    ): PreferencesResponse

//  Order


    @GET("users/profile/{user_id}")
    suspend fun profile(
        @Path("user_id") userId: Int,
    ): LoginResult

    @Multipart
    @PUT("users/update/{user_id}")
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

    @FormUrlEncoded
    @PUT("users/update/{user_id}")
    suspend fun updateUser(
        @Path("user_id") userId: Int,
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("address") address: String,
    ): UpdateProfileResponse

//   Product
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("location") location: Int = 0,
    ): ProductsResponse

    @Multipart
    @POST("stories")
    suspend fun uploadStories(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): ErrorMessageResponse


//    @POST("orders/items")
//    suspend fun addOrderItem(
//        @Field("userID") userID: Int,
//        @Field("product_id") productID: Int,
//    ): ErrorMessageResponse

    @GET("news")
    suspend fun getNews(): NewsResponse


//   Order
    @Headers("Content-Type: application/json")
    @POST("orders/create")
    suspend fun createOrder(
        @Body order: OrderResponse,
    ): ErrorMessageResponse



}
