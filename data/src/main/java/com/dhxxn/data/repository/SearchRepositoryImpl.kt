package com.dhxxn.data.repository

import com.dhxxn.data.datasource.SearchDataSource
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList
import com.dhxxn.domain.common.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
): SearchRepository {

    override suspend fun requestSearchKeyword(keyword: String): NetworkResponse<DogList> {
        return searchDataSource.requestSearchKeyword(keyword)
    }
}