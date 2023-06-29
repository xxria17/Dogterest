package com.dhxxn.baseapi

import com.dhxxn.baseapi.error.ErrorHandlerImpl
import com.dhxxn.domain.common.ErrorData
import com.dhxxn.domain.common.ResultWrapper
import retrofit2.Response

class BaseApi : ErrorHandlerImpl() {

    override suspend fun <ResultType, RequestType> getApi(
        remoteFetch: suspend () -> Response<RequestType>,
        mapping: (RequestType) -> ResultType
    ): ResultWrapper<ResultType> = handleResponse({ remoteFetch() }, mapping)

    private suspend fun <RequestType, ResultType> handleResponse(
        call: suspend () -> Response<RequestType>,
        converter: (RequestType) -> ResultType
    ): ResultWrapper<ResultType> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let {
                    return ResultWrapper.Success(
                        data = converter(it),
                        code = response.code()
                    )
                }
            }
            return ResultWrapper.Failed(
                error = ErrorData.Api(response.errorBody()?.string())
            )
        } catch (throwable: Throwable) {
            ResultWrapper.Failed(getError(throwable))
        }
    }
}