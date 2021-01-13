package com.kw.project.module.core.data.source.remote

import com.kw.project.module.core.BuildConfig
import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.utils.ApiResultWrapper
import com.kw.project.module.core.utils.EmptyArgumentSingletonHolder
import com.kw.project.module.core.utils.RemoteDataSourceHelper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
class GithubRemoteDataSource: GithubDataSource {
    companion object: EmptyArgumentSingletonHolder<GithubRemoteDataSource>(:: GithubRemoteDataSource)

    private val githubWebService: GithubWebService by lazy {
        Retrofit.Builder()
            .client(RemoteDataSourceHelper.getOkHttpClient())
            .baseUrl("${BuildConfig.BASE_URL}")
            .addConverterFactory(GsonConverterFactory.create(RemoteDataSourceHelper.getGson()))
            .build()
            .create(GithubWebService::class.java)
    }

    override suspend fun getTreadingRepository(): ApiResultWrapper<SearchResult> = RemoteDataSourceHelper.safeApiCall { githubWebService.getTreadingRepository() }
}