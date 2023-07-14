package com.dhxxn.domain.common.usecase

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList
import com.dhxxn.domain.common.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun requestSearchKeyword(keyword: String): NetworkResponse<DogList> {
        return searchRepository.requestSearchKeyword(keyword)
    }
}