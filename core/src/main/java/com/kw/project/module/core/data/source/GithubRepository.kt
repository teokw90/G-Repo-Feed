package com.kw.project.module.core.data.source

import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.utils.ApiResultWrapper

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
interface GithubRepository {
    suspend fun getTreadingRepository(): ApiResultWrapper<SearchResult>
}