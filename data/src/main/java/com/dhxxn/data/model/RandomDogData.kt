package com.dhxxn.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomDogData(
    @field:Json(name = "message") val imageUrl: String,
    @field:Json(name = "status") val status: String
)