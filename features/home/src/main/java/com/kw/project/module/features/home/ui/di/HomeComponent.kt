package com.kw.project.module.features.home.ui.di

import com.kw.project.module.core.di.CoreComponent
import com.kw.project.module.core.di.scopes.FeatureScope
import com.kw.project.module.features.home.ui.HomeFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [HomeModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [HomeModule::class]
)
interface HomeComponent {
    /**
     * Inject dependencies on component.
     *
     * @param homeFragment home component.
     */
    fun inject(homeFragment: HomeFragment)
}