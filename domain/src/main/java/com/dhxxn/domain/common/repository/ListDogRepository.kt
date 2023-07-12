package com.dhxxn.domain.common.repository

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList

interface ListDogRepository {
    suspend fun requestDogList(offset: Int): NetworkResponse<DogList>
}