package com.dhxxn.data.model

import com.google.gson.annotations.SerializedName

data class ResponseDogsData (
    @SerializedName("message") val dogList: List<String>,
    @SerializedName("status") val status: String
)