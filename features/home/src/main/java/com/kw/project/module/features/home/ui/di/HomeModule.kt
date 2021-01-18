package com.kw.project.module.features.home.ui.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.kw.project.module.core.data.source.GithubRepository
import com.kw.project.module.core.di.scopes.FeatureScope
import com.kw.project.module.features.home.ui.HomeFragment
import com.kw.project.module.features.home.ui.HomeViewModel
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [HomeComponent].
 *
 * @see Module
 */
@Module
class HomeModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: HomeFragment
){
    /**
     * Create a provider method binding for [HomeViewModel].
     *
     * @return Instance of view model.
     * @see Provides
     */
    @Provides
    @FeatureScope
    fun providesHomeViewModel(githubRepository: GithubRepository) = HomeViewModel(githubRepository = githubRepository)
}