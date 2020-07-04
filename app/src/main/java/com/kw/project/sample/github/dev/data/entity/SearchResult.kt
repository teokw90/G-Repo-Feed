package com.kw.project.sample.github.dev.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

data class SearchResult(
    @SerializedName("total_count") val count: Int,
    @SerializedName("incomplete_results") val isResultIncomplete: Boolean = false,
    @SerializedName("items") val results: List<RepositoryInfo>
)