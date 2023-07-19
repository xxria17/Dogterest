package com.dhxxn.data.model

import com.google.gson.annotations.SerializedName

data class ResponseDogData(
    @SerializedName("message") val imageUrl: String,
    @SerializedName("status") val status: String
)