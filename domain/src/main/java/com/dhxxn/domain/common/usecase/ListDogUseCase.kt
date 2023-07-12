package com.dhxxn.domain.common.usecase

import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList
import com.dhxxn.domain.common.repository.ListDogRepository
import javax.inject.Inject

class ListDogUseCase @Inject constructor(
    private val listDogRepository: ListDogRepository
) {
    suspend operator fun invoke(offset: Int): NetworkResponse<DogList> {
        return listDogRepository.requestDogList(offset)
    }
}