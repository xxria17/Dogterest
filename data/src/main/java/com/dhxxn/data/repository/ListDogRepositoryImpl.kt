package com.dhxxn.data.repository

import com.dhxxn.data.datasource.ListDogDataSource
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.DogList
import com.dhxxn.domain.common.repository.ListDogRepository
import javax.inject.Inject

class ListDogRepositoryImpl @Inject constructor(
    private val listDogDataSource: ListDogDataSource
): ListDogRepository {
    override suspend fun requestDogList(offset: Int): NetworkResponse<DogList> {
        return listDogDataSource.requestListDog(offset)
    }
}