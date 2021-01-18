package com.kw.project.module.core.di.modules

import com.google.gson.GsonBuilder
import com.kw.project.module.core.BuildConfig
import com.kw.project.module.core.data.source.remote.GithubWebService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class WebServiceModule {
    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(BuildConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(false)
        .build()

    private val gson = GsonBuilder().apply {
        setDateFormat(BuildConfig.SERVER_DATE_TIME_FORMAT)
    }.create()

    @Provides
    @Singleton
    fun provideGithubWebService(): GithubWebService = Retrofit.Builder()
        .baseUrl("${BuildConfig.BASE_URL}")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(GithubWebService::class.java)
}