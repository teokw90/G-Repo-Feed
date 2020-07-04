package com.kw.project.sample.github.dev.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kw.project.sample.github.dev.data.entity.RepositoryInfo
import com.kw.project.sample.github.dev.databinding.ListItemSearchResultBinding

/**
 * Created by Kuan Wah Teo on 02/05/2020
 **/

class SearchResultsAdapter: ListAdapter<RepositoryInfo, SearchResultsAdapter.SearchResultItemViewHolder>(SearchResultDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder = SearchResultItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: SearchResultItemViewHolder, position: Int) = holder.bind(getItem(position))

    class SearchResultItemViewHolder(private val binding: ListItemSearchResultBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): SearchResultItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSearchResultBinding.inflate(layoutInflater, parent, false)

                return SearchResultItemViewHolder(binding)
            }
        }

        fun bind(item: RepositoryInfo) {
            binding.repositoryInfo = item
            binding.ownerInfo = item.owner
            binding.executePendingBindings()
        }
    }
}

class SearchResultDiffCallback: DiffUtil.ItemCallback<RepositoryInfo>() {
    override fun areItemsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean
        = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RepositoryInfo, newItem: RepositoryInfo): Boolean
        = oldItem == newItem
}