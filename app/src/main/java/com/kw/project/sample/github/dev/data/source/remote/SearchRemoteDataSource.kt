package com.kw.project.sample.github.dev.data.source.remote

import com.kw.project.sample.github.dev.BuildConfig
import com.kw.project.sample.github.dev.data.entity.SearchResult
import com.kw.project.sample.github.dev.utils.ApiResultWrapper
import com.kw.project.sample.github.dev.utils.EmptyArgumentSingletonHolder
import com.kw.project.sample.github.dev.utils.RemoteDataSourceHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

class SearchRemoteDataSource: SearchDataSource {
    companion object: EmptyArgumentSingletonHolder<SearchRemoteDataSource>(:: SearchRemoteDataSource)

    private val webService: SearchWebService by lazy {
        Retrofit.Builder()
            .client(RemoteDataSourceHelper.getOkHttpClient())
            .baseUrl("${BuildConfig.BASE_URL}")
            .addConverterFactory(GsonConverterFactory.create(RemoteDataSourceHelper.getGson()))
            .build()
            .create(SearchWebService::class.java)
    }

    override suspend fun getTrendRepository(): ApiResultWrapper<SearchResult>
        = RemoteDataSourceHelper.safeApiCall {  webService.getTrendingRepository() }
}