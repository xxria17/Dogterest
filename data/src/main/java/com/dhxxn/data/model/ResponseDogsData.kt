package com.dhxxn.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDogsData (
    @field:Json(name = "message") val dogList: List<String>,
    @field:Json(name = "status") val status: String
)