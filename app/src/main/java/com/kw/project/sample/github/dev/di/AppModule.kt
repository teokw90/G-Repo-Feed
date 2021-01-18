package com.kw.project.sample.github.dev.di

import android.content.Context
import com.kw.project.sample.github.dev.MainApplication
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {
    /**
     * Create a provider method binding for [Context].
     *
     * @param application
     * @return Instance of context
     * @see Provides
     */
    @Provides
    fun provideContext(application: MainApplication): Context = application.applicationContext
}