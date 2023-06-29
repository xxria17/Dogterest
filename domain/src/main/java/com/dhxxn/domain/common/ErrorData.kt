package com.dhxxn.domain.common

import java.io.IOException

sealed class ErrorData(
    val message: String? = null,
    val code: Int? = null,
    val errorBody: String? = null
) {

    object Network: ErrorData()

    class Database(message: String): ErrorData(message = message)

    class Server(message: String): ErrorData(message = message)

    class Unknown(message: String): ErrorData(message = message)

    class Api(message: String?, code: Int? = null, errorBody: String? = null):
            ErrorData(message = message, code = code, errorBody = errorBody)

}

class NoInternetException(message: String? = null): IOException(message)

class ServerConnectionException(message: String? = null): IOException(message)


