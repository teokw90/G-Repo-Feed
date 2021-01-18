package com.kw.project.module.core.data.source

import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.data.source.remote.GithubDataSource
import com.kw.project.module.core.utils.ApiResultWrapper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
@Singleton
class DefaultGithubRepository
@Inject constructor(private val githubRemoteDataSource: GithubDataSource): GithubRepository {
    override suspend fun getTreadingRepository(): ApiResultWrapper<SearchResult> =
        githubRemoteDataSource.getTreadingRepository()
}