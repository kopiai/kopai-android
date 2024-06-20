package com.kopai.shinkansen.data.remote.retrofit

import com.kopai.shinkansen.data.remote.response.BlendResponse
import com.kopai.shinkansen.data.remote.response.ErrorMessageResponse
import com.kopai.shinkansen.data.remote.response.LoginResponse
import com.kopai.shinkansen.data.remote.response.PreferencesResponse
import com.kopai.shinkansen.data.remote.response.ProductsResponse
import okhttp3.MultipartBody
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


//   Stories
    @GET("stories")
    suspend fun getProducts(
        @Query("page") page: Int?,
        @Query("size") size: Int?,
        @Query("location") location: Int = 0,
    ): ProductsResponse

    @Multipart
    @POST("stories")
    suspend fun uploadProduct(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): ErrorMessageResponse


//   Product

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
    ): ErrorMessageResponse

    @JvmSuppressWildcards
    @GET("preferences/{user_id}")
    suspend fun getPreferences(
        @Path("user_id") userId: String,
    ): PreferencesResponse



//   Order
}
