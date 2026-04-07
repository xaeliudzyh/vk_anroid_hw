package com.example.vkapplication.data.dto

import com.google.gson.annotations.SerializedName

data class NetworkAppDto(
    val id: String?,
    val name: String?,
    val developer: String?,
    val category: String?,
    val rating: Float?,
    @SerializedName(value = "reviewCount", alternate = ["reviewsCount"])
    val reviewCount: String?,
    @SerializedName(value = "downloadsCount", alternate = ["downloadCount"])
    val downloadsCount: String?,
    val size: String?,
    val description: String?,
    @SerializedName(value = "iconUrl", alternate = ["icon", "icon_url"])
    val iconUrl: String?,
    val isFree: Boolean?
)
