package com.kw.project.module.core.data.source.remote

import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.utils.ApiResultWrapper

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
class FakeGithubRemoteDataSource(var searchResult: SearchResult? = null): GithubDataSource {
    override suspend fun getTreadingRepository(): ApiResultWrapper<SearchResult> {
        searchResult?.let {
            return ApiResultWrapper.Success(it)
        }
        return ApiResultWrapper.GenericError(errorCode = -1, errorMessage = "Unknown")
    }
}