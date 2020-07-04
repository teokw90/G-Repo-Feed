package com.kw.project.sample.github.dev.search

import android.os.Build
import android.view.*
import androidx.fragment.app.viewModels
import com.kw.project.sample.github.dev.data.source.SearchRepositoryImpl
import com.kw.project.sample.github.dev.data.source.remote.SearchRemoteDataSource


import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.webkit.RenderProcessGoneDetail
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration

import com.kw.project.sample.github.dev.R
import com.kw.project.sample.github.dev.data.entity.RepositoryInfo
import com.kw.project.sample.github.dev.databinding.FragmentSearchBinding
import com.kw.project.sample.github.dev.utils.ApiResultWrapper
import com.kw.project.sample.github.dev.utils.NavigationIconClickListener


class SearchFragment : Fragment() {
    private val searchViewModel by viewModels<SearchViewModel> {
        SearchViewModel.SearchViewModelFactory(SearchRepositoryImpl.getInstance(SearchRemoteDataSource.getInstance()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSearchBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        // Setup the toolbar.
        (activity as AppCompatActivity).setSupportActionBar(binding.appBar)
        binding.appBar.setNavigationOnClickListener(NavigationIconClickListener(
            requireActivity(),
            binding.searchPageGroup,
            AccelerateDecelerateInterpolator(),
            ContextCompat.getDrawable(requireContext(), R.drawable.gd_menu),
            ContextCompat.getDrawable(requireContext(), R.drawable.gd_close)
        ))

        // Set cut corner background for API 23+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.searchPageGroup.background = ContextCompat.getDrawable(requireContext(), R.drawable.gd_search_background_shape)
        }

        binding.searchViewModel = searchViewModel
        binding.lifecycleOwner = this@SearchFragment

        // Setup Recycler View
        val searchResultsAdapter = SearchResultsAdapter()
        binding.searchResultsRecyclerView.adapter = searchResultsAdapter
        binding.searchResultsRecyclerView.addItemDecoration(DividerItemDecoration(binding.searchResultsRecyclerView.context, DividerItemDecoration.VERTICAL))

        searchViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            when(it) {
                is ApiResultWrapper.Success -> {
                    binding.unableLoadGroup.visibility = View.GONE
                    binding.searchResultsRecyclerView.visibility = View.VISIBLE

                    searchResultsAdapter.submitList(it.value.results)
                }
                is ApiResultWrapper.GenericError -> {
                    binding.unableLoadGroup.visibility = View.VISIBLE
                    binding.searchResultsRecyclerView.visibility = View.GONE

                    binding.unableLoadMessage.text = it.errorMessage
                    searchResultsAdapter.submitList(ArrayList<RepositoryInfo>())
                }
                is ApiResultWrapper.NetworkError -> {
                    binding.unableLoadGroup.visibility = View.VISIBLE
                    binding.searchResultsRecyclerView.visibility = View.GONE

                    searchResultsAdapter.submitList(ArrayList<RepositoryInfo>())
                }
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.github_dev_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
