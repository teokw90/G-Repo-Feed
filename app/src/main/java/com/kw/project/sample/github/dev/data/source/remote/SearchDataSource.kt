package com.kw.project.sample.github.dev.data.source.remote

import com.kw.project.sample.github.dev.data.entity.SearchResult
import com.kw.project.sample.github.dev.utils.ApiResultWrapper

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

interface SearchDataSource {
    suspend fun getTrendRepository(): ApiResultWrapper<SearchResult>
}