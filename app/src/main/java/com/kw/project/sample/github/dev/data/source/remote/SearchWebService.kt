package com.kw.project.sample.github.dev.data.source.remote

import com.kw.project.sample.github.dev.data.entity.SearchResult
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

interface SearchWebService {
    @Headers("ContentType: application/json")
    @GET("search/repositories?q=stars:>500+topic:android+language:kotlin+pushed:>=2020-03-31+is:public&sort=updated&order=desc")
    suspend fun getTrendingRepository(): SearchResult
}