package com.dhxxn.data

import com.dhxxn.data.model.ResponseDogData
import com.dhxxn.data.model.ResponseDogsData
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiService {
    @GET("image/random")
    suspend fun requestRandomImage(): Response<ResponseDogData>

    @GET("image/random/{offset}")
    suspend fun requestRandomDogList(): Response<ResponseDogsData>
}