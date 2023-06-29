package com.dhxxn.data.datasource

import com.dhxxn.domain.common.ResultWrapper
import com.dhxxn.domain.common.model.Dog

interface RandomDogDataSource {

    suspend fun requestRandomDog(): ResultWrapper<Dog>
}