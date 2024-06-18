package com.kopai.shinkansen.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProvincesResponse(
    @field:SerializedName("ProvincesResponse")
    val provincesResponse: List<ProvinceResponseItem>,
)

data class ProvinceResponseItem(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("alt_name")
    val altName: String,
    @field:SerializedName("latitude")
    val latitude: Double,
    @field:SerializedName("longitude")
    val longitude: Double,
)

data class CitiesResponse(
    @field:SerializedName("CitiesResponse")
    val citiesResponse: List<CityResponseItem>,
)

data class CityResponseItem(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("alt_name")
    val altName: String,
    @field:SerializedName("latitude")
    val latitude: Double,
    @field:SerializedName("longitude")
    val longitude: Double,
)
