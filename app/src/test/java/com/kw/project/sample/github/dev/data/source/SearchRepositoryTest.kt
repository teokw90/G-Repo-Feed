package com.kw.project.sample.github.dev.data.source

import com.kw.project.sample.github.dev.data.entity.OwnerInfo
import com.kw.project.sample.github.dev.data.entity.RepositoryInfo
import com.kw.project.sample.github.dev.data.entity.SearchResult
import com.kw.project.sample.github.dev.data.source.remote.FakeSearchRemoteDataSource
import com.kw.project.sample.github.dev.data.source.remote.SearchDataSource
import com.kw.project.sample.github.dev.utils.ApiResultWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
@ExperimentalCoroutinesApi
class SearchRepositoryTest {
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

    private val searchResult =  SearchResult(
        count = 1,
        isResultIncomplete = false,
        results = listOf(repositoryInfo)
    )

    private val remoteSuccessData = ApiResultWrapper.Success(searchResult)
    private val remoteGenericErrorData = ApiResultWrapper.GenericError(errorCode = -1, errorMessage = "Unknown")

    private lateinit var searchRemoteDataSource: SearchDataSource

    // Class under test
    private lateinit var searchRepository: SearchRepository

    @Test
    fun getTrendingRepository_requestsFromRemoteDataSource_returnSuccess() = runBlockingTest {
        // GIVEN - Fake data source
        searchRemoteDataSource = FakeSearchRemoteDataSource(searchResult)
        // Get a reference to the class under test
        searchRepository = SearchRepositoryImpl(searchRemoteDataSource)

        // WHEN - trending repository are requested from the search repository
        val searchResult = searchRepository.getTrendingRepository() as ApiResultWrapper.Success

        // THEN - trending repository are loaded from the remote data source
        assertThat(searchResult, IsEqual(remoteSuccessData))
    }

    @Test
    fun getTrendingRepository_requestsFromRemoteDataSource_returnGenericError() = runBlockingTest {
        // GIVEN - Fake data source
        searchRemoteDataSource = FakeSearchRemoteDataSource()
        // Get a reference to the class under test
        searchRepository = SearchRepositoryImpl(searchRemoteDataSource)

        // WHEN - trending repository are requested from the search repository
        val searchResult = searchRepository.getTrendingRepository() as ApiResultWrapper.GenericError

        // THEN - trending repository are loaded from the remote data source
        assertThat(searchResult, IsEqual(remoteGenericErrorData))
    }
}