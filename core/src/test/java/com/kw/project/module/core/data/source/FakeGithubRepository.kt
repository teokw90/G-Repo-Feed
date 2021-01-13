package com.kw.project.module.core.data.source

import com.kw.project.module.core.data.entity.OwnerInfo
import com.kw.project.module.core.data.entity.RepositoryInfo
import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.utils.ApiResultWrapper

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
class FakeGithubRepository: GithubRepository {
    private val ownerInfo = OwnerInfo(
        login = "Dummy",
        id = 3521738,
        nodeId = "MDQ6VXNlcjM1MjE3Mzg=",
        avatarUrl = "https://avatars3.githubusercontent.com/u/3521738?v=0"
    )

    private val repositoryInfo = RepositoryInfo(
        id = 43807251,
        nodeId = "MDEwOlJlcG9zaXRvcnk0MzgwNzI1MQ==",
        name = "Dummy",
        fullName = "Dummy/Dummy",
        isPrivate = false,
        owner = ownerInfo,
        htmlUrl = "https://github.com/Dummy/Dummy",
        description = "Fake Description"
    )

    private var searchResult =  SearchResult(
        count = 1,
        isResultIncomplete = false,
        results = listOf(repositoryInfo)
    )

    override suspend fun getTreadingRepository(): ApiResultWrapper<SearchResult> {
        return ApiResultWrapper.Success(searchResult)
    }

    fun addSearchResult(vararg repositoryInfo: RepositoryInfo) {
        val repositoryInfoList = ArrayList<RepositoryInfo>()

        for(item in repositoryInfo) {
            repositoryInfoList.add(item)
            searchResult = SearchResult(
                count = repositoryInfo.size,
                isResultIncomplete = false,
                results = repositoryInfoList
            )
        }
    }
}