package com.dhxxn.data.datasource

import com.dhxxn.baseapi.BaseApi
import com.dhxxn.data.NetworkApiService
import com.dhxxn.data.mapper.ConvertMapper
import com.dhxxn.data.model.RandomDogData
import com.dhxxn.domain.common.ResultWrapper
import com.dhxxn.domain.common.model.Dog
import javax.inject.Inject

class RandomDogDataSourceImpl @Inject constructor(
    private val apiService: NetworkApiService
): RandomDogDataSource {

    private val baseApi = BaseApi()

    override suspend fun requestRandomDog(): ResultWrapper<Dog> = baseApi.getApi(
        remoteFetch = {
            apiService.requestRandomImage()
        },
        mapping = {
            ConvertMapper<RandomDogData, Dog>() (
                it
            )
        }
    )
}