package com.dhxxn.data

import com.dhxxn.data.model.ResponseDogData
import com.dhxxn.data.model.ResponseDogsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApiService {
    @GET("breeds/image/random")
    suspend fun requestRandomImage(): Response<ResponseDogData>

    @GET("breeds/image/random/{offset}")
    suspend fun requestRandomDogList(@Path(value = "offset") offset: Int): Response<ResponseDogsData>

    @GET("breed/{breed}/images")
    suspend fun requestSearchKeyword(@Path(value = "breed") keyword: String): Response<ResponseDogsData>
}