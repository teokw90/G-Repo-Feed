package com.kw.project.module.core.data.source.remote

import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.utils.ApiResultWrapper
import com.kw.project.module.core.utils.RemoteDataSourceHelper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
@Singleton
class GithubRemoteDataSource
@Inject constructor(private val githubWebService: GithubWebService): GithubDataSource {

    override suspend fun getTreadingRepository(): ApiResultWrapper<SearchResult> = RemoteDataSourceHelper.safeApiCall { githubWebService.getTreadingRepository() }
}