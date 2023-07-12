package com.dhxxn.data.datasource

import com.dhxxn.data.NetworkApiService
import com.dhxxn.data.mapper.randomMapper
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.Dog
import javax.inject.Inject

class RandomDogDataSourceImpl @Inject constructor(
    private val apiService: NetworkApiService
): RandomDogDataSource {
    override suspend fun requestRandomDog(): NetworkResponse<Dog> {
        return randomMapper(
            apiService.requestRandomImage()
        )
    }

}