package com.kw.project.module.features.home.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import com.kw.project.module.core.data.entity.RepositoryInfo
import com.kw.project.module.core.data.source.DefaultGithubRepository
import com.kw.project.module.core.data.source.remote.GithubRemoteDataSource
import com.kw.project.module.core.utils.ApiResultWrapper
import com.kw.project.module.features.home.R
import com.kw.project.module.features.home.databinding.FragmentHomeBinding
import com.kw.project.module.common.utils.NavigationIconClickListener
import com.kw.project.module.features.home.ui.di.DaggerHomeComponent
import com.kw.project.module.features.home.ui.di.HomeModule
import com.kw.project.sample.github.dev.MainApplication
import javax.inject.Inject

class HomeFragment: Fragment() {
    @Inject 
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerHomeComponent
            .builder()
            .coreComponent(MainApplication.coreComponent(requireContext()))
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        // Setup the toolbar.
        (activity as AppCompatActivity).setSupportActionBar(binding.appBar)
        binding.appBar.setNavigationOnClickListener(
            NavigationIconClickListener(
            requireActivity(),
            binding.searchPageGroup,
            AccelerateDecelerateInterpolator(),
            ContextCompat.getDrawable(requireContext(), R.drawable.gd_menu),
            ContextCompat.getDrawable(requireContext(), R.drawable.gd_close)
        )
        )

        // Set cut corner background for API 23+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.searchPageGroup.background = ContextCompat.getDrawable(requireContext(), R.drawable.gd_search_background_shape)
        }

        binding.homeViewModel = viewModel
        binding.lifecycleOwner = this@HomeFragment

        // Setup Recycler View
        val searchResultsAdapter = SearchResultsAdapter()
        binding.searchResultsRecyclerView.adapter = searchResultsAdapter
        binding.searchResultsRecyclerView.addItemDecoration(DividerItemDecoration(binding.searchResultsRecyclerView.context, DividerItemDecoration.VERTICAL))

        viewModel.searchResult.observe(viewLifecycleOwner, Observer {
            when(it) {
                is ApiResultWrapper.Success -> {
                    binding.unableLoadMessage.visibility = View.GONE
                    binding.retry.visibility = View.GONE
                    binding.searchResultsRecyclerView.visibility = View.VISIBLE

                    searchResultsAdapter.submitList(it.value.results)
                }
                is ApiResultWrapper.GenericError -> {
                    binding.unableLoadMessage.visibility = View.VISIBLE
                    binding.retry.visibility = View.VISIBLE
                    binding.searchResultsRecyclerView.visibility = View.GONE

                    binding.unableLoadMessage.text = it.errorMessage
                    searchResultsAdapter.submitList(ArrayList<RepositoryInfo>())
                }
                is ApiResultWrapper.NetworkError -> {
                    binding.unableLoadMessage.visibility = View.VISIBLE
                    binding.retry.visibility = View.VISIBLE
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