package com.kw.project.module.core.di.modules

import com.kw.project.module.core.data.source.DefaultGithubRepository
import com.kw.project.module.core.data.source.GithubRepository
import com.kw.project.module.core.data.source.remote.GithubRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideGithubRepository(remoteDataSource: GithubRemoteDataSource): GithubRepository = DefaultGithubRepository(remoteDataSource)
}