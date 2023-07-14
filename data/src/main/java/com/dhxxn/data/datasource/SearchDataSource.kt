package com.dhxxn.data.datasource

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList

interface SearchDataSource {
    suspend fun requestSearchKeyword(keyword: String): NetworkResponse<DogList>
}