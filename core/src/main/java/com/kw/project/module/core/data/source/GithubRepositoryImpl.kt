package com.kw.project.module.core.data.source

import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.data.source.remote.GithubDataSource
import com.kw.project.module.core.utils.ApiResultWrapper
import com.kw.project.module.core.utils.SingletonHolder

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
class GithubRepositoryImpl(private val githubRemoteDataSource: GithubDataSource): GithubRepository {
    companion object: SingletonHolder<GithubRepository, GithubDataSource>(:: GithubRepositoryImpl)

    override suspend fun getTreadingRepository(): ApiResultWrapper<SearchResult> =
        githubRemoteDataSource.getTreadingRepository()
}