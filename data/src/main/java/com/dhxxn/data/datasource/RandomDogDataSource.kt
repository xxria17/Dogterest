package com.dhxxn.data.datasource

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.Dog

interface RandomDogDataSource {
    suspend fun requestRandomDog(): NetworkResponse<Dog>
}