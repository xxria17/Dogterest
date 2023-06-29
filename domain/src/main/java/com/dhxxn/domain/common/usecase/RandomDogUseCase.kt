package com.dhxxn.domain.common.usecase

import com.dhxxn.domain.common.ResultWrapper
import com.dhxxn.domain.common.model.Dog
import com.dhxxn.domain.common.repository.RandomDogRepository
import javax.inject.Inject

class RandomDogUseCase @Inject constructor(
    private val randomDogRepository: RandomDogRepository
) {

    suspend operator fun invoke(): ResultWrapper<Dog> {
        return randomDogRepository.requestRandomDog()
    }

}