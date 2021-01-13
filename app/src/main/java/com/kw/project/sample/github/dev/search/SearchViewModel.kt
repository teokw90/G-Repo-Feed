package com.kw.project.sample.github.dev.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.data.source.GithubRepository
import com.kw.project.module.core.utils.ApiResultWrapper
import kotlinx.coroutines.*

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

class SearchViewModel(private val githubRepository: GithubRepository): ViewModel() {
    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _searchResult = MutableLiveData<ApiResultWrapper<SearchResult>>()
    val searchResult: LiveData<ApiResultWrapper<SearchResult>>
        get() = _searchResult

    init {
        getTrendingRepository()
    }

    fun getTrendingRepository() {
        coroutineScope.launch {
            val response = githubRepository.getTreadingRepository()

            withContext(Dispatchers.Main) {
                _searchResult.value = response
            }
        }
    }

    @Suppress("UNCHECKED_CASE")
    class SearchViewModelFactory(private val githubRepository: GithubRepository): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (SearchViewModel(githubRepository) as T)
    }
}