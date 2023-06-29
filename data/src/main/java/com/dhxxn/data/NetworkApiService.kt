package com.dhxxn.data

import com.dhxxn.data.model.RandomDogData
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiService {

    @GET("image/random")
    suspend fun requestRandomImage(): Response<RandomDogData>


}