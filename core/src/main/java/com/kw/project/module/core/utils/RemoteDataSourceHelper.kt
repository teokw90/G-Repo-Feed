package com.kw.project.module.core.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kw.project.module.core.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
class RemoteDataSourceHelper {
    companion object {
        fun getOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(BuildConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(false)
            .build()

        fun getGson(): Gson = GsonBuilder().apply {
            setDateFormat(BuildConfig.SERVER_DATE_TIME_FORMAT)
        }.create()

        suspend fun <T> safeApiCall(apiCall: suspend() -> T): ApiResultWrapper<T> {
            return withContext(Dispatchers.IO) {
                try {
                    ApiResultWrapper.Success(apiCall.invoke())
                } catch(throwable: Throwable) {
                    when(throwable) {
                        is IOException -> ApiResultWrapper.NetworkError
                        is HttpException -> ApiResultWrapper.GenericError(throwable.code(), throwable.message())
                        else -> ApiResultWrapper.GenericError(errorCode = -1, errorMessage = "Unknown")
                    }
                }
            }
        }
    }
}

sealed class ApiResultWrapper<out T> {
    data class Success<out T>(val value: T): ApiResultWrapper<T>()
    data class GenericError(val errorCode: Int, val errorMessage: String): ApiResultWrapper<Nothing>()
    object NetworkError: ApiResultWrapper<Nothing>()
}