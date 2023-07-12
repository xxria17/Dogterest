package com.dhxxn.data.datasource

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList

interface ListDogDataSource {
    suspend fun requestListDog(offset:Int): NetworkResponse<DogList>
}