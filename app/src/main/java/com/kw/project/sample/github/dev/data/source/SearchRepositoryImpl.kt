package com.kw.project.sample.github.dev.data.source

import com.kw.project.sample.github.dev.data.entity.SearchResult
import com.kw.project.sample.github.dev.data.source.remote.SearchDataSource
import com.kw.project.sample.github.dev.utils.ApiResultWrapper
import com.kw.project.sample.github.dev.utils.SingletonHolder

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

class SearchRepositoryImpl(private val remoteDataSource: SearchDataSource): SearchRepository{
    companion object: SingletonHolder<SearchRepository, SearchDataSource>(:: SearchRepositoryImpl)

    override suspend fun getTrendingRepository(): ApiResultWrapper<SearchResult>
        = remoteDataSource.getTrendRepository()
}