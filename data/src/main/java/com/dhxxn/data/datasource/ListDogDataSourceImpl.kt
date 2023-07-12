package com.dhxxn.data.datasource

import com.dhxxn.data.NetworkApiService
import com.dhxxn.data.mapper.listMapper
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList
import javax.inject.Inject

class ListDogDataSourceImpl @Inject constructor(
    private val apiService: NetworkApiService
): ListDogDataSource {
    override suspend fun requestListDog(offset: Int): NetworkResponse<DogList> {
        return listMapper(
            response= apiService.requestRandomDogList(),
        )
    }
}