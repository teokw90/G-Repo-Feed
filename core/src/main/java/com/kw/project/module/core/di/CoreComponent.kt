package com.kw.project.module.core.di

import com.kw.project.module.core.data.source.GithubRepository
import com.kw.project.module.core.di.modules.RepositoryModule
import com.kw.project.module.core.di.modules.WebServiceModule
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        WebServiceModule::class
    ]
)
interface CoreComponent {
    fun githubRepository(): GithubRepository
}