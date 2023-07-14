package com.dhxxn.domain.common.repository

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList

interface SearchRepository {
    suspend fun requestSearchKeyword(keyword: String): NetworkResponse<DogList>
}