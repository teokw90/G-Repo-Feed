package com.kw.project.sample.github.dev.data.source

import com.kw.project.sample.github.dev.data.entity.SearchResult
import com.kw.project.sample.github.dev.utils.ApiResultWrapper

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

interface SearchRepository {
    suspend fun getTrendingRepository(): ApiResultWrapper<SearchResult>
}