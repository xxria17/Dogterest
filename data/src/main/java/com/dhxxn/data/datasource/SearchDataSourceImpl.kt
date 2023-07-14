package com.dhxxn.data.datasource

import com.dhxxn.data.NetworkApiService
import com.dhxxn.data.mapper.listMapper
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val apiService: NetworkApiService
) : SearchDataSource {

    override suspend fun requestSearchKeyword(keyword: String): NetworkResponse<DogList> {
        return listMapper(
            apiService.requestSearchKeyword(keyword)
        )
    }

}