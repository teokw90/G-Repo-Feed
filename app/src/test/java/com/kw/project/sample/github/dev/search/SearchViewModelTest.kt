package com.kw.project.sample.github.dev.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kw.project.sample.github.dev.data.entity.OwnerInfo
import com.kw.project.sample.github.dev.data.entity.RepositoryInfo
import com.kw.project.sample.github.dev.data.entity.SearchResult
import com.kw.project.sample.github.dev.data.source.FakeSearchRepository
import com.kw.project.sample.github.dev.getOrAwaitValue
import com.kw.project.sample.github.dev.utils.ApiResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
@ExperimentalCoroutinesApi
class SearchViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    // Use a fake repository to be injected into viewModel
    private lateinit var searchRepository: FakeSearchRepository

    // Subject under test
    private lateinit var searchViewModel: SearchViewModel

    // Executes each task synchronously using Architecture Components
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        Dispatchers.setMain(testDispatcher)

        // We initial the repository with 1 search result
        searchRepository = FakeSearchRepository()
        searchRepository.addSearchResult(injectFakeSearchResult())

        // GIVEN a fresh viewModel
        searchViewModel = SearchViewModel(searchRepository)
    }

    @Test
    fun getTrendingRepository_returnListOfSearchResult() = runBlocking {
        // WHEN - Get Treading Repository
        searchViewModel.getTrendingRepository()

        // THEN - The search result is triggered
        var result = searchViewModel.searchResult.getOrAwaitValue().run {
            when(this) {
                is ApiResultWrapper.Success -> ApiResultWrapper.Success(this.value)
                is ApiResultWrapper.GenericError -> ApiResultWrapper.GenericError(this.errorCode, this.errorMessage)
                else -> ApiResultWrapper.NetworkError
            }
        }

        assertEquals(result, ApiResultWrapper.Success(SearchResult(
            count = listOf(injectFakeSearchResult()).size,
            isResultIncomplete = false,
            results = listOf(injectFakeSearchResult())
        )))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    private fun injectFakeSearchResult(): RepositoryInfo {
        val ownerInfo = OwnerInfo(
            login = "Dummy",
            id = 3521738,
            nodeId = "MDQ6VXNlcjM1MjE3Mzg=",
            avatarUrl = "https://avatars3.githubusercontent.com/u/3521738?v=0"
        )

        return RepositoryInfo(
            id = 43807251,
            nodeId = "MDEwOlJlcG9zaXRvcnk0MzgwNzI1MQ==",
            name = "Dummy",
            fullName = "Dummy/Dummy",
            isPrivate = false,
            owner = ownerInfo,
            htmlUrl = "https://github.com/Dummy/Dummy",
            description = "Fake Description"
        )
    }
}