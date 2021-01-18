package com.kw.project.module.features.home.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kw.project.module.core.data.entity.SearchResult
import com.kw.project.module.core.data.source.GithubRepository
import com.kw.project.module.core.utils.ApiResultWrapper
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Created by Kuan Wah Teo on 02/05/2020
 */
class HomeViewModel
@Inject constructor(private val githubRepository: GithubRepository): ViewModel() {
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
    class HomeViewModelFactory(private val githubRepository: GithubRepository): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (HomeViewModel(githubRepository) as T)
    }
}