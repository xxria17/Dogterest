package com.dhxxn.domain.common.usecase

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.Dog
import com.dhxxn.domain.common.repository.RandomDogRepository
import javax.inject.Inject

class RandomDogUseCase @Inject constructor(
    private val randomDogRepository: RandomDogRepository
) {
    suspend fun requestRandomImage(): NetworkResponse<Dog> {
        return randomDogRepository.requestRandomDog()
    }
}