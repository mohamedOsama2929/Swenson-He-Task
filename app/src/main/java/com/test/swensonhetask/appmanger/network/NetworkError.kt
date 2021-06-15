package com.test.swensonhetask.appmanger.network

import retrofit2.HttpException
import java.io.IOException

fun netWorkError(ex: Throwable): String? {

    return when (ex) {
        is IOException -> ex.message.toString()
        is HttpException -> ex.code().toString()
        else -> ex.message.toString()
    }

}