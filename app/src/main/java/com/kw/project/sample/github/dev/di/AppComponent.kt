package com.kw.project.sample.github.dev.di

import com.kw.project.module.core.di.CoreComponent
import com.kw.project.module.core.di.scopes.AppScope
import com.kw.project.sample.github.dev.MainApplication
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    /**
     * Inject dependencies on application.
     *
     * @param application
     */
    fun inject(application: MainApplication)
}