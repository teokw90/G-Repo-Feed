package com.kw.project.module.core.data.source.remote

import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.utils.ApiResultWrapper

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
interface GithubDataSource {
    suspend fun getTreadingRepository(): ApiResultWrapper<SearchResult>
}