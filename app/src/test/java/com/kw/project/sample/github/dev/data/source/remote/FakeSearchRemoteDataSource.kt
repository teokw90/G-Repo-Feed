package com.kw.project.sample.github.dev.data.source.remote

import com.kw.project.sample.github.dev.data.entity.SearchResult
import com.kw.project.sample.github.dev.utils.ApiResultWrapper

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

class FakeSearchRemoteDataSource(var searchResult: SearchResult? = null): SearchDataSource {
    override suspend fun getTrendRepository(): ApiResultWrapper<SearchResult> {
        searchResult?.let {
            return ApiResultWrapper.Success(it)
        }
        return ApiResultWrapper.GenericError(errorCode = -1, errorMessage = "Unknown")
    }
}