package com.example.vkapplication.data.network

import com.example.vkapplication.data.dto.NetworkAppDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApi {
    @GET("catalog")
    suspend fun getCatalog(): List<NetworkAppDto>

    @GET("catalog/{id}")
    suspend fun getAppById(@Path("id") id: String): NetworkAppDto
}
