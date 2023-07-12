package com.dhxxn.data.mapper

import com.dhxxn.data.model.ResponseDogData
import com.dhxxn.data.model.ResponseDogsData
import com.dhxxn.domain.common.NetworkResponse
import com.dhxxn.domain.common.model.Dog
import com.dhxxn.domain.common.model.DogList
import retrofit2.Response

// Response<Any> -> NetworkResponse<Any>
fun listMapper(
    response: Response<ResponseDogsData>
): NetworkResponse<DogList> {
    if (response.isSuccessful) {
        response.body()?.let {
            return NetworkResponse.Success(
                body = DogList(
                    list = it.dogList,
                    status = it.status
                )
            )
        }
    }
    return NetworkResponse.ApiError(
        message = response.message(),
        code = response.code()
    )
}

fun randomMapper(
    response: Response<ResponseDogData>
): NetworkResponse<Dog> {
    if (response.isSuccessful) {
        response.body()?.let {
            return NetworkResponse.Success(
                body = Dog(
                    imageUrl = it.imageUrl,
                    status = it.status
                )
            )
        }
    }
    return NetworkResponse.ApiError(
        message = response.message(),
        code = response.code()
    )
}