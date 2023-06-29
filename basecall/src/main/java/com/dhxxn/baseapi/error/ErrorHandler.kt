package com.dhxxn.baseapi.error

import com.dhxxn.domain.common.ErrorData
import com.dhxxn.domain.common.ResultWrapper
import retrofit2.Response

interface ErrorHandler {

    suspend fun <ResultType, RequestType> getApi(
        remoteFetch: suspend () -> Response<RequestType>,
        mapping: (RequestType) -> ResultType
    ): ResultWrapper<ResultType>

    fun getError(throwable: Throwable): ErrorData
}