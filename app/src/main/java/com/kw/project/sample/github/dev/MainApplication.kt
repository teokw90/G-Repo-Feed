package com.kw.project.sample.github.dev

import android.app.Application
import android.content.Context
import com.kw.project.module.core.di.CoreComponent
import com.kw.project.module.core.di.DaggerCoreComponent
import com.kw.project.sample.github.dev.di.DaggerAppComponent

class MainApplication: Application() {
    lateinit var coreComponent: CoreComponent

    companion object {
        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as? MainApplication)?.coreComponent
    }
    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .build()
    }

    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

}