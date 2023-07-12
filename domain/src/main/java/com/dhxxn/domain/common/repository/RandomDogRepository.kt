package com.dhxxn.domain.common.repository

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.Dog

interface RandomDogRepository {
    suspend fun requestRandomDog(): NetworkResponse<Dog>
}