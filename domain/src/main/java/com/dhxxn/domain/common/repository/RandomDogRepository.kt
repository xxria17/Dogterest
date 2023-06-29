package com.dhxxn.domain.common.repository

import com.dhxxn.domain.common.ResultWrapper
import com.dhxxn.domain.common.model.Dog

interface RandomDogRepository {

    suspend fun requestRandomDog(): ResultWrapper<Dog>
}