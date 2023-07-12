package com.dhxxn.data.repository

import com.dhxxn.data.datasource.RandomDogDataSource
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.Dog
import com.dhxxn.domain.common.repository.RandomDogRepository
import javax.inject.Inject

class RandomDogRepositoryImpl @Inject constructor(
    private val randomDogDataSource: RandomDogDataSource
): RandomDogRepository {

    override suspend fun requestRandomDog(): NetworkResponse<Dog> {
        return randomDogDataSource.requestRandomDog()
    }
}