package com.kw.project.module.core.data.source.remote

import com.kw.project.module.core.data.entity.SearchResult
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
interface GithubWebService {
    @Headers("ContentType: application/jsob")
    @GET("search/repositories?q=stars:>500+topic:android+language:kotlin+pushed:>=2020-03-31+is:public&sort=updated&order=desc")
    suspend fun getTreadingRepository(): SearchResult
}